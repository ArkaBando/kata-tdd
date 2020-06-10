package com.kata.utility;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.kata.exception.StringCalculatorException;

/**
 * StringCalculator is an utility class for performing addition of numbers based
 * on kata problem as methioned in https://osherove.com/tdd-kata-2/
 * 
 * @author ARKA
 *
 */
public final class StringCalculator {
	private static final List<String> delimiters = new ArrayList<>();

	/**
	 * Utility method for adding numbers , Mainly it parses the given numbers string
	 * and delegates computation to calculateSum()
	 * 
	 * @param numbers like 1,2,3,4,5
	 * @return calculatedSum of numbers
	 * @throws StringCalculatorException when number is negative or numbers is in
	 *                                   invalid format
	 */
	public BigInteger add(String numbers) throws StringCalculatorException {

		final AtomicReference<BigInteger> sum = new AtomicReference<>(BigInteger.ZERO);
		final List<String> negativeNumbers = new ArrayList<>();

		try {

			if (filterAndValidateArgument(numbers) == 0) {
				return BigInteger.ZERO;
			}
			String splitter = ",|\\n";
			if (delimiters.size() > 0) {
				numbers = numbers.substring(numbers.indexOf("\n") + 1);
				StringBuilder splitters = new StringBuilder(splitter);
				fetchSpiltter(splitters);
				splitter = splitters.toString();
			}

			Arrays.asList(numbers.split(splitter)).forEach(number -> {
				calculateSum(sum, negativeNumbers, number);
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

	private void fetchSpiltter(StringBuilder splitters) {
		for (String delimiter : delimiters) {
			if (splitters.toString().indexOf(delimiter) != 0) {
				delimiter = delimiter.replace("*", "\\*");
				delimiter = delimiter.replace("+", "\\+");
				delimiter = delimiter.replace("^", "\\^");
				splitters.append("|").append(delimiter);
			}
		}
	}

	/**
	 * Method used for computing sum based on parsed values
	 * 
	 * @param sum              : sum of parsed numbers
	 * @param negativeNumbers: list of negative numbers contained in user input
	 *                         numbers string
	 * @param number           : parsed number on which computation needs to be
	 *                         performed
	 */
	private void calculateSum(final AtomicReference<BigInteger> sum, final List<String> negativeNumbers,
			String number) {
		if (!StringUtils.isEmpty(number)) {
			sum.updateAndGet((BigInteger currentNumber) -> {
				BigInteger value = new BigInteger(number);
				if (value.compareTo(BigInteger.ZERO) < 0) {
					negativeNumbers.add(value.toString());
					return currentNumber;
				} else if (value.compareTo(new BigInteger("1000")) > 0) {
					return currentNumber;
				}
				return currentNumber.add(value);
			});
		}
	}

	private void addDelimetersFromText(String text) {
		Pattern regex = Pattern.compile("\\[(.*?)\\]");
		Matcher regexMatcher = regex.matcher(text);

		while (regexMatcher.find()) {
			delimiters.add(regexMatcher.group(1));
		}

	}

	/**
	 * For filtering and validating user input numbers
	 * 
	 * @param numbers
	 * @return
	 * @throws StringCalculatorException
	 */
	private Integer filterAndValidateArgument(String numbers) throws StringCalculatorException {

		if (null != numbers && numbers.indexOf("//") == 0) {
			String delimiterContainningText = numbers.substring(2, numbers.indexOf("\n"));
			addDelimetersFromText(delimiterContainningText);
			// delimiters.add(delimiter);
		}

		if (null != numbers && numbers.trim().equals("")) {
			return 0;
		} else if (StringUtils.isEmpty(numbers)) {
			throw new StringCalculatorException("Null or Empty numbers are not allowed");
		} else if (!isValidNumber(numbers)) {
			throw new StringCalculatorException(
					"Invalid String , numbers is either alphanumeric or it is improperly delimited");
		}

		return 1;
	}

	/**
	 * Used for validating user number string
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isValidNumber(String text) {
		if (null != text && delimiters.size() > 0) {
			text = text.substring(text.indexOf("\n") + 1);
			for (String delimiter : delimiters) {
				text = text.replace(delimiter, "");
			}
		}
		return text != null && text.matches("^[0-9,\\n-]*$");
	}
}
