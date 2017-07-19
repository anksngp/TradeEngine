package com.ms.assignment.tradeengine.exception;

/**
 * Customized exception class for invalud input
 * 
 * @author Anks
 *
 */
public class WrongInputException extends RuntimeException {

	/* serial version id */
	private static final long serialVersionUID = 1L;

	/* The error message */
	private String mesaage;

	/**
	 * Constructor for error
	 * 
	 * @param message
	 */
	public WrongInputException(String message) {
		this.mesaage = message;
	}

	/**
	 * Getter for error message
	 * 
	 * @return
	 */
	public String getMesaage() {
		return mesaage;
	}

}
