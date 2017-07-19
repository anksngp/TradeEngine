package com.ms.assignment.tradeengine.model;

import org.junit.Test;

import com.ms.assignment.tradeengine.exception.WrongInputException;
import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.model.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

	@Test
	public void testTradeAmountCalculator() {

		BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
		BigDecimal agreedFx = BigDecimal.valueOf(0.50);
		int units = 200;
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(pricePerUnit).withAgreedFx(agreedFx).withUnits(units)
				.withSettlementDate(LocalDate.of(2017, 3, 20)).build();

		final BigDecimal correct = pricePerUnit.multiply(agreedFx).multiply(BigDecimal.valueOf(units)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		assertEquals(correct, instruction.getTradeAmount());
	}

	@Test(expected = WrongInputException.class)
	public void testTradeAmountCalcWrongInputNullAfreeFx() throws Exception {

		BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
		BigDecimal agreedFx = null;
		int units = 200;
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(pricePerUnit).withAgreedFx(agreedFx).withUnits(units)
				.withSettlementDate(LocalDate.of(2017, 3, 20)).build();

	}

	@Test(expected = WrongInputException.class)
	public void testTradeAmountCalcWrongInputNullPricepernit() throws Exception {

		BigDecimal pricePerUnit = null;
		BigDecimal agreedFx = BigDecimal.valueOf(0.50);
		int units = 200;
		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(pricePerUnit).withAgreedFx(agreedFx).withUnits(units)
				.withSettlementDate(LocalDate.of(2017, 3, 20)).build();

	}
}