package com.ms.assignment.tradeengine.util;

import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.workingdays.GulfWorkingDays;
import com.ms.assignment.tradeengine.workingdays.GeneralWorkingDays;
import com.ms.assignment.tradeengine.workingdays.WorkingDays;

/**
 * Settlement date calculator
 * 
 * @author Anks
 *
 */
public class SettlementDateCalculator {

	private static Logger logger = Logger.getLogger(SettlementDateCalculator.class.getName());

	/**
	 * computes the settlement date, can be different than provided in
	 * instruction in case of a non working day
	 * 
	 * @param instructions
	 * @return
	 */
	public static Set<Instruction> computeSettlementDates(Set<Instruction> instructions) {
		Set<Instruction> instructionSet = new HashSet<>();
		for (Instruction instruction : instructions) {
			instructionSet.add(computeSettlementDate(instruction));
		}
		return instructionSet;
	}

	static Instruction computeSettlementDate(Instruction instruction) {
		final WorkingDays workingDays = getWorkingDaysStrategy(instruction.getCurrency());

		final LocalDate newSettlementDate = workingDays.findWorkingDate(instruction.getSettlementDate());

		if (newSettlementDate != null) {
			return new Instruction.InstructionBuilder().withEntity(instruction.getEntity()).withAction(instruction.getAction())
					.withInstructionDate(instruction.getInstructionDate()).withCurrency(instruction.getCurrency()).withPricePerUnit(instruction.getPricePerUnit())
					.withAgreedFx(instruction.getAgreedFx()).withUnits(instruction.getUnits()).withSettlementDate(newSettlementDate).build();
		}
		return instruction;
	}

	/**
	 * Find working day based on currency
	 * 
	 * @param currency
	 * @return
	 */
	private static WorkingDays getWorkingDaysStrategy(Currency currency) {
		logger.info("Getting working days based on currency");
		if ((currency.getCurrencyCode().equals("AED")) || (currency.getCurrencyCode().equals("SAR"))) {
			return GulfWorkingDays.getInstance();
		}
		return GeneralWorkingDays.getInstance();
	}
}
