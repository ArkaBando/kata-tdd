package com.kata.utility;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;

import com.kata.exception.StringCalculatorException;

public final class StringCalculator {

	public BigInteger add(String numbers) throws StringCalculatorException {

		if (filterAndValidateArgument(numbers) == 0) {
			return BigInteger.ZERO;
		}

		final AtomicReference<BigInteger> sum = new AtomicReference<>(BigInteger.ZERO);

		Arrays.asList(numbers.split(",")).forEach(number -> {

			sum.updateAndGet((BigInteger no) -> {
					return no.add(new BigInteger(number));
			});
			// sum.addAndGet(Integer.parseInt(number));
		});

		return sum.get();
	}

	private Integer filterAndValidateArgument(String numbers) throws StringCalculatorException {
		if (null != numbers && numbers.trim().equals("")) {
			return 0;
		} else if (StringUtils.isEmpty(numbers)) {
			throw new StringCalculatorException("Null or Empty numbers are not allowed");
		} else if (!isNumericWithCommaDelimiter(numbers)) {
			throw new StringCalculatorException(
					"Invalid String , numbers is either alphanumeric or it is improperly delimited");
		}
		return 1;
	}

	public static boolean isNumericWithCommaDelimiter(String s) {
		return s != null && s.matches("^[0-9,\\n]*$");
	}
}
