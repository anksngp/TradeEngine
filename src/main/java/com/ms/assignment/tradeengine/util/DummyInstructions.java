package com.ms.assignment.tradeengine.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.model.TradeAction;

public class DummyInstructions {
	public static Set<Instruction> getDummyInstructions() {

		Set<Instruction> instructions = new HashSet<>();

		Instruction instruction = new Instruction.InstructionBuilder().withEntity("Ins1").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SGD")).withPricePerUnit(BigDecimal.valueOf(100.25)).withAgreedFx(BigDecimal.valueOf(0.50)).withUnits(200)
				.withSettlementDate(LocalDate.of(2017, 3, 20)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins2").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("AED")).withPricePerUnit(BigDecimal.valueOf(150.5)).withAgreedFx(BigDecimal.valueOf(0.22)).withUnits(450)
				.withSettlementDate(LocalDate.of(2017, 3, 19)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins3").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("SAR")).withPricePerUnit(BigDecimal.valueOf(400.8)).withAgreedFx(BigDecimal.valueOf(0.27)).withUnits(150)
				.withSettlementDate(LocalDate.of(2017, 3, 18)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins4").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("EUR")).withPricePerUnit(BigDecimal.valueOf(500.6)).withAgreedFx(BigDecimal.valueOf(0.34)).withUnits(50)
				.withSettlementDate(LocalDate.of(2017, 3, 21)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins5").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("EUR")).withPricePerUnit(BigDecimal.valueOf(40.6)).withAgreedFx(BigDecimal.valueOf(0.34)).withUnits(20)
				.withSettlementDate(LocalDate.of(2017, 3, 21)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins6").withAction(TradeAction.BUY).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("EUR")).withPricePerUnit(BigDecimal.valueOf(40.6)).withAgreedFx(BigDecimal.valueOf(0.34)).withUnits(20)
				.withSettlementDate(LocalDate.of(2017, 3, 21)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins7").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("EUR")).withPricePerUnit(BigDecimal.valueOf(160.6)).withAgreedFx(BigDecimal.valueOf(0.34)).withUnits(1000)
				.withSettlementDate(LocalDate.of(2017, 3, 21)).build();
		instructions.add(instruction);

		instruction = new Instruction.InstructionBuilder().withEntity("Ins8").withAction(TradeAction.SELL).withInstructionDate(LocalDate.of(2017, 3, 10))
				.withCurrency(Currency.getInstance("EUR")).withPricePerUnit(BigDecimal.valueOf(500.6)).withAgreedFx(BigDecimal.valueOf(0.34)).withUnits(120)
				.withSettlementDate(LocalDate.of(2017, 3, 21)).build();
		instructions.add(instruction);

		return instructions;

	}
}
