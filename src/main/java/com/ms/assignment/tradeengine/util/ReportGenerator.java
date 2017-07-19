package com.ms.assignment.tradeengine.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.ms.assignment.tradeengine.model.Instruction;

/**
 * Class to generate report
 * 
 * @author Anks
 *
 */
public class ReportGenerator {

	Logger logger = Logger.getLogger(ReportGenerator.class.getName());

	public void generateInstructionsReport(Set<Instruction> instructions) {

		logger.info("Generating reports");
		generateDailyOutgoingAmount(instructions);
		generateDailyIncomingAmount(instructions);
		generateDailyIncomingRanking(instructions);
		generateDailyOutgoingRanking(instructions);
	}

	/**
	 * Generate outgoing amount report
	 * 
	 * @param instructions
	 */
	private static void generateDailyOutgoingAmount(Set<Instruction> instructions) {

		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = StatisticsCalculator.calculateDailyOutgoingAmount(instructions);
		String message = "Outgoing amount";
		printAmount(dailyOutgoingAmount, message);

	}

	/**
	 * Generate incoming amount report
	 * 
	 * @param instructions
	 */
	private static void generateDailyIncomingAmount(Set<Instruction> instructions) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = StatisticsCalculator.calculateDailyIncomingAmount(instructions);
		String message = "Incoming amount";
		printAmount(dailyOutgoingAmount, message);

	}

	private static void printAmount(final Map<LocalDate, BigDecimal> dailyOutgoingAmount, String message) {
		System.out.println(message);
		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			System.out.println(date + " " + dailyOutgoingAmount.get(date));
		}
	}

	/**
	 * Generate outgoing amount rank report
	 * 
	 * @param instructions
	 */
	private static void generateDailyOutgoingRanking(Set<Instruction> instructions) {
		final Map<LocalDate, LinkedList<Instruction>> dailyOutgoingRanking = StatisticsCalculator.calculateDailyOutgoingRanking(instructions);
		String message = "Outgoing amount rank";
		printRank(dailyOutgoingRanking, message);

	}

	/**
	 * Generate incoming amount rank report
	 * 
	 * @param instructions
	 */
	private static void generateDailyIncomingRanking(Set<Instruction> instructions) {
		final Map<LocalDate, LinkedList<Instruction>> dailyIncomingRanking = StatisticsCalculator.calculateDailyIncomingRanking(instructions);
		String message = "Incoming amount rank";
		printRank(dailyIncomingRanking, message);

	}

	private static void printRank(final Map<LocalDate, LinkedList<Instruction>> dailyOutgoingRanking, String message) {
		System.out.println(message);

		for (LocalDate date : dailyOutgoingRanking.keySet()) {
			int i = 1;
			for (Instruction rank : dailyOutgoingRanking.get(date)) {
				System.out.println(date + " " + i + " " + rank.getEntity());
				i++;
			}
		}
	}

}
