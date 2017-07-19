package com.ms.assignment.tradeengine.util;

import org.junit.Test;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.model.TradeAction;
import com.ms.assignment.tradeengine.util.SettlementDateCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class SettlementDateCalculatorTest {
	@Test
	public void testSettlementDateFridaySGD() throws Exception {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24);
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction computeSettlementDate = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(initialSettlementDate, computeSettlementDate.getSettlementDate());
	}

	@Test
	public void testSettlementDateSundayUSD() {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26);
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("USD")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction newInstruction = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(LocalDate.of(2017, 3, 27), newInstruction.getSettlementDate());
	}

	@Test
	public void testSetllementDateGulfFridayAED() {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24);
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("AED")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction newInstruction = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(LocalDate.of(2017, 3, 26), newInstruction.getSettlementDate());
	}
	

	@Test
	public void testSetllementDateGulfFridaySAR() {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 24);
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SAR")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction newInstruction = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(LocalDate.of(2017, 3, 26), newInstruction.getSettlementDate());
	}

	@Test
	public void testSettlementDateGulfSundayAED()  {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("AED")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction newInstruction = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(initialSettlementDate, newInstruction.getSettlementDate());
	}
	

	@Test
	public void testSettlementDateGulfSundaySAR()  {
		final LocalDate initialSettlementDate = LocalDate.of(2017, 3, 26); // Its
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SAR")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(initialSettlementDate).build();
		Instruction newInstruction = SettlementDateCalculator.computeSettlementDate(instruction);
		assertEquals(initialSettlementDate, newInstruction.getSettlementDate());
	}
}