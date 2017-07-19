package com.ms.assignment.tradeengine.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.ms.assignment.tradeengine.exception.WrongInputException;

/**
 * Instruction class
 * 
 * @author Anks
 *
 */
public class Instruction {

	/* A financial entity whose shares are to be bought or sold */
	private final String entity;

	/* Buy or sell type */
	private final TradeAction action;

	/* Instruction submit date */
	private final LocalDate instructionDate;

	/* Instruction settlement date */
	private final LocalDate settlementDate;

	/* Currency */
	private final Currency currency;

	/* the foreign exchange rate with respect to USD that was agreed */
	private final BigDecimal agreedFx;

	/* Number of shares to be bought or sold */
	private final int units;

	/* Price per unit of shares */
	private final BigDecimal pricePerUnit;

	/* the total instruction amount */
	private final BigDecimal tradeAmount;

	/**
	 * @param entity
	 * @param action
	 * @param instructionDate
	 * @param settlementDate
	 * @param currency
	 * @param agreedFx
	 * @param units
	 * @param pricePerUnit
	 * @param tradeAmount
	 */
	private Instruction(String entity, TradeAction action, LocalDate instructionDate, LocalDate settlementDate, Currency currency, BigDecimal agreedFx, int units,
			BigDecimal pricePerUnit, BigDecimal tradeAmount) {
		this.entity = entity;
		this.action = action;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.currency = currency;
		this.agreedFx = agreedFx;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.tradeAmount = calculateAmount(pricePerUnit, units, agreedFx);
	}

	private static BigDecimal calculateAmount(BigDecimal pricePerUnit, int units, BigDecimal agreedFx) {
		if (pricePerUnit == null || agreedFx == null) {
			throw new WrongInputException("Invalid pricePerUnit or agreedFx");
		}
		return pricePerUnit.multiply(BigDecimal.valueOf(units)).multiply(agreedFx);
	}

	public String getEntity() {
		return entity;
	}

	public TradeAction getAction() {
		return action;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public static class InstructionBuilder {
		private String entity;
		private TradeAction action;
		private LocalDate instructionDate;
		private LocalDate settlementDate;
		private Currency currency;
		private BigDecimal agreedFx;
		private int units;
		private BigDecimal pricePerUnit;
		private BigDecimal tradeAmount;

		public InstructionBuilder withEntity(String entity) {
			this.entity = entity;
			return this;
		}

		public InstructionBuilder withAction(TradeAction action) {
			this.action = action;
			return this;
		}

		public InstructionBuilder withInstructionDate(LocalDate instructionDate) {
			this.instructionDate = instructionDate;
			return this;
		}

		public InstructionBuilder withSettlementDate(LocalDate settlementDate) {
			this.settlementDate = settlementDate;
			return this;
		}

		public InstructionBuilder withCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public InstructionBuilder withAgreedFx(BigDecimal agreedFx) {
			this.agreedFx = agreedFx;
			return this;
		}

		public InstructionBuilder withUnits(int units) {
			this.units = units;
			return this;
		}

		public InstructionBuilder withPricePerUnit(BigDecimal pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
			return this;
		}

		public InstructionBuilder withTradeAmount(BigDecimal tradeAmount) {
			this.tradeAmount = tradeAmount;
			return this;
		}

		public Instruction build() {
			return new Instruction(entity, action, instructionDate, settlementDate, currency, agreedFx, units, pricePerUnit, tradeAmount);
		}
	}

	public static InstructionBuilder instruction() {
		return new InstructionBuilder();
	}

}
