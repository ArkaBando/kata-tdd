package com.kata.exception;

public class StringCalculatorException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exceptionMessage;

	public StringCalculatorException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	@Override
	public String toString() {
		return exceptionMessage;
	}

}
