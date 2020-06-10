package com.kata.utility;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.kata.exception.StringCalculatorException;

public final class StringCalculator {

	public Integer add(String numbers) throws StringCalculatorException {

		
		if (StringUtils.isEmpty(numbers)) {
			throw new StringCalculatorException("Null or Empty numbers are not allowed");
		} else if(!isNumericWithCommaDelimiter(numbers)) {
			throw new StringCalculatorException("Invalid String , numbers is either alphanumeric or it is improperly delimited");
		}

		final AtomicInteger sum = new AtomicInteger(0);

		Arrays.asList(numbers.split(",")).forEach(number -> {
			sum.addAndGet(Integer.parseInt(number));
		});

		return sum.get();
	}

	public static boolean isNumericWithCommaDelimiter(String s) {
		return s != null && s.matches("^[0-9,]*$");
	}
}
