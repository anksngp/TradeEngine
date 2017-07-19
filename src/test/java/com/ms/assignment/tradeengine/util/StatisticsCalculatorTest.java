package com.ms.assignment.tradeengine.util;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.model.TradeAction;

public class StatisticsCalculatorTest {

	private static final LocalDate MONDAY = LocalDate.of(2017, 3, 20);
	private static final LocalDate TUESDAY = LocalDate.of(2017, 3, 21);
	private static final LocalDate WEDNESDAY = LocalDate.of(2017, 3, 22);
	private static final LocalDate SATURDAY = LocalDate.of(2017, 3, 18);
	private static final LocalDate SUNDAY = LocalDate.of(2017, 3, 19);

	private static Set<Instruction> getDummyInstructions() {
		final Set<Instruction> instructions = new HashSet<>();

		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(100)
				.withSettlementDate(MONDAY).build();
		instructions.add(instruction);

		Instruction instruction2 = new Instruction.InstructionBuilder().withEntity("Ins2").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(200)
				.withSettlementDate(MONDAY).build();

		instructions.add(instruction2);

		Instruction instruction3 = new Instruction.InstructionBuilder().withEntity("Ins3").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(300)
				.withSettlementDate(SATURDAY).build();

		instructions.add(instruction3);

		Instruction instruction4 = new Instruction.InstructionBuilder().withEntity("Ins4").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(200)
				.withSettlementDate(SUNDAY).build();
		instructions.add(instruction4);

		Instruction instruction5 = new Instruction.InstructionBuilder().withEntity("Ins5").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(400)
				.withSettlementDate(TUESDAY).build();
		instructions.add(instruction5);

		Instruction instruction6 = new Instruction.InstructionBuilder().withEntity("Ins6").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(1000)
				.withSettlementDate(TUESDAY).build();

		instructions.add(instruction6);

		Instruction instruction7 = new Instruction.InstructionBuilder().withEntity("Ins7").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(1)).withAgreedFx(BigDecimal.valueOf(1)).withUnits(700)
				.withSettlementDate(WEDNESDAY).build();
		instructions.add(instruction7);

		Set<Instruction> computeSettlementDates = SettlementDateCalculator.computeSettlementDates(instructions);

		return computeSettlementDates;
	}

	@Test
	public void testDailyIncomingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyIncomingAmount = StatisticsCalculator.calculateDailyIncomingAmount(getDummyInstructions());

		assertEquals(2, dailyIncomingAmount.size());
		assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
		assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(1000.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
	}

	@Test
	public void testDailyOutgoingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = StatisticsCalculator.calculateDailyOutgoingAmount(getDummyInstructions());

		assertEquals(3, dailyOutgoingAmount.size());
		assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
		assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(400.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
		assertTrue(Objects.equals(dailyOutgoingAmount.get(WEDNESDAY), BigDecimal.valueOf(700.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
	}

	@Test
	public void testDailyIncomingRanking() throws Exception {
		Set<Instruction> dummyInstructions = getDummyInstructions();
		final Map<LocalDate, LinkedList<Instruction>> dailyIncomingRanking = StatisticsCalculator.calculateDailyIncomingRanking(dummyInstructions);

		assertEquals(2, dailyIncomingRanking.size());

		LinkedList<Instruction> linkedList = dailyIncomingRanking.get(MONDAY);
		assertEquals(1, linkedList.size());
		LinkedList<Instruction> linkedList2 = dailyIncomingRanking.get(TUESDAY);
		assertEquals(1, linkedList2.size());

	}

	@Test
	public void testDailyOutgoingRanking() throws Exception {
		final Map<LocalDate, LinkedList<Instruction>> dailyOutgoingRanking = StatisticsCalculator.calculateDailyOutgoingRanking(getDummyInstructions());

		assertEquals(3, dailyOutgoingRanking.size());

		assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
		assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
		assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

	}
}