package com.ms.assignment.tradeengine;

import java.util.Set;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.util.DummyInstructions;
import com.ms.assignment.tradeengine.util.ReportGenerator;
import com.ms.assignment.tradeengine.util.SettlementDateCalculator;

/**
 * Main class to compute and generate report
 * 
 * @author Anks
 *
 */
public class Application {

	/**
	 * Method to compute and generate report
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Set<Instruction> instructions = DummyInstructions.getDummyInstructions();
		Set<Instruction> newInstructions = SettlementDateCalculator.computeSettlementDates(instructions);
		final ReportGenerator reportGenerator = new ReportGenerator();
		reportGenerator.generateInstructionsReport(newInstructions);
	}
}
