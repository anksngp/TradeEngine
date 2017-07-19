package com.ms.assignment.tradeengine.util;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.ms.assignment.tradeengine.model.Instruction;
import com.ms.assignment.tradeengine.model.TradeAction;

/**
 * StatisticsCalculator
 * 
 * @author Anks
 *
 */
public class StatisticsCalculator {

	private static Logger logger = Logger.getLogger(StatisticsCalculator.class.getName());

	/**
	 * Outgoing instrution predicate
	 */
	private final static Predicate<Instruction> outgoingInstructionsPredicate = instruction -> instruction.getAction().equals(TradeAction.BUY);

	/**
	 * Incoming instrution predicate
	 */
	private final static Predicate<Instruction> incomingInstructionsPredicate = instruction -> instruction.getAction().equals(TradeAction.SELL);

	/**
	 * Compute map of settlement date with total amount on that date for
	 * outgoing amount
	 * 
	 * @param instructions
	 * @return
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Instruction> instructions) {
		logger.info("Getting daily outgoing map");
		Map<LocalDate, BigDecimal> map = new HashMap<>();
		for (Instruction instruction : instructions) {
			if (instruction.getAction().equals(TradeAction.BUY)) {
				LocalDate instructionDate = instruction.getSettlementDate();
				BigDecimal value = map.get(instructionDate);
				if (null != value) {
					value = value.add(instruction.getTradeAmount());
					map.put(instructionDate, value);
				} else {
					map.put(instructionDate, instruction.getTradeAmount());
				}
			}
		}
		return map;
	}

	/**
	 * Compute map of settlement date with total amount on that date for
	 * incoming amount
	 * 
	 * @param instructions
	 * @return
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Instruction> instructions) {
		logger.info("Getting daily incoming map");
		Map<LocalDate, BigDecimal> map = new HashMap<>();
		for (Instruction instruction : instructions) {
			if (instruction.getAction().equals(TradeAction.SELL)) {
				LocalDate instructionDate = instruction.getSettlementDate();
				BigDecimal value = map.get(instructionDate);
				if (null != value) {
					value = value.add(instruction.getTradeAmount());
					map.put(instructionDate, value);
				} else {
					map.put(instructionDate, instruction.getTradeAmount());
				}
			}
		}
		return map;
	}

	/**
	 * Compute map of settlement date with all instruction on that date for
	 * outgoing amount
	 * 
	 * @param instructions
	 * @return
	 */
	public static Map<LocalDate, LinkedList<Instruction>> calculateDailyOutgoingRanking(Set<Instruction> instructions) {
		return calculateRanking(instructions, outgoingInstructionsPredicate);
	}

	/**
	 * Compute map of settlement date with all instruction on that date for
	 * incoming amount
	 * 
	 * @param instructions
	 * @return
	 */
	public static Map<LocalDate, LinkedList<Instruction>> calculateDailyIncomingRanking(Set<Instruction> instructions) {
		return calculateRanking(instructions, incomingInstructionsPredicate);
	}

	private static Map<LocalDate, LinkedList<Instruction>> calculateRanking(Set<Instruction> instructions, Predicate<Instruction> predicate) {

		logger.info("Calculating ranking");

		final Map<LocalDate, LinkedList<Instruction>> ranking = new HashMap<>();

		Map<LocalDate, Set<Instruction>> collect = instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate, toSet()));
		Set<Entry<LocalDate, Set<Instruction>>> entrySet = collect.entrySet();
		for (Entry<LocalDate, Set<Instruction>> entry : entrySet) {
			LinkedList<Instruction> linkedList = new LinkedList<>();
			LocalDate key = entry.getKey();
			Set<Instruction> value = entry.getValue();
			linkedList.addAll(value);
			Collections.sort(linkedList, (p1, p2) -> p2.getTradeAmount().compareTo(p1.getTradeAmount()));
			ranking.put(key, linkedList);

		}

		return ranking;
	}
}
