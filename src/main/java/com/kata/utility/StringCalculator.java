package com.kata.utility;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;

import com.kata.exception.StringCalculatorException;

public final class StringCalculator {
	private static final List<String> delimiters = new ArrayList<>();

	public BigInteger add(String numbers) throws StringCalculatorException {

		final AtomicReference<BigInteger> sum = new AtomicReference<>(BigInteger.ZERO);
		final List<String> negativeNumbers = new ArrayList<>();

		try {
			if (filterAndValidateArgument(numbers) == 0) {
				return BigInteger.ZERO;
			}

			if (delimiters.size() > 0) {
				numbers = numbers.substring(numbers.indexOf("\n") + 1);
			}

			Arrays.asList(numbers.split(",|\\n" + (delimiters.size() > 0 ? "|" + delimiters.get(0) : "")))
					.forEach(number -> {

						if (!StringUtils.isEmpty(number)) {
							sum.updateAndGet((BigInteger no) -> {
								if (new BigInteger(number).compareTo(BigInteger.ZERO) < 0) {
									negativeNumbers.add(new BigInteger(number).toString());
									return no;
								}
								return no.add(new BigInteger(number));
							});
						}
					});
		} finally {
			delimiters.clear();
		}

		if (negativeNumbers.size() > 0) {
			StringBuilder negativeNumberResult = new StringBuilder();
			negativeNumbers.forEach(negativeNumberResult::append);
			throw new StringCalculatorException("NegativeNumbers :" + negativeNumberResult + " Not Allowed");
		}
		return sum.get();
	}

	private Integer filterAndValidateArgument(String numbers) throws StringCalculatorException {

		if (null != numbers && numbers.indexOf("//") == 0) {
			String delimiter = numbers.substring(2, numbers.indexOf("\n"));
			delimiters.add(delimiter);
		}

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
		if (null != s && delimiters.size() > 0) {
			s = s.replace(delimiters.get(0), "").replace("//", "");
		}
		return s != null && s.matches("^[0-9,\\n-]*$");
	}
}
