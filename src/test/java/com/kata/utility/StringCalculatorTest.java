package com.kata.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kata.exception.StringCalculatorException;

public class StringCalculatorTest {

	private static StringCalculator calculator;

	@BeforeAll
	public static void setup() {
		calculator = new StringCalculator();
	}

	@Test
	public void isStringCalculatorInitializedProperly() {
		assertNotNull(calculator);
	}

	@Test
	public void testAdd() throws StringCalculatorException {
		BigInteger result = calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED);
		assertNotNull(result);
		assertEquals(TestUtils.expectedResultMap.get("NUMBERS_WITH_COMMA_DELIMITED"), result.intValue());
	}

	@Test
	public void testAddWithCustomSymbolAsDelimiter() throws StringCalculatorException {
		BigInteger result = calculator.add(TestUtils.NUMBERS_WITH_CUSTOM_SYMBOL_DELIMITED);
		assertNotNull(result);
		assertEquals(TestUtils.expectedResultMap.get("NUMBERS_WITH_COMMA_DELIMITED"), result.intValue());
	}

	@Test
	public void testAddWithCommaAndNewlineDelimited() throws StringCalculatorException {
		BigInteger result = calculator.add(TestUtils.NUMBERS_WITH_COMMA_AND_NEWLINE_DELIMITED);
		assertNotNull(result);
		assertEquals(TestUtils.expectedResultMap.get("NUMBERS_WITH_COMMA_DELIMITED"), result.intValue());
	}

	@Test
	public void testAddForEmptyString() throws StringCalculatorException {
		BigInteger result = calculator.add("");
		assertNotNull(result);
		assertEquals(0, result.intValue());
	}

	@Test
	public void testAddWithNonTrimmedSpaceInsideNumbers() throws StringCalculatorException {
		String message = Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_WITHOUT_SPACE_TRIMMED_VALUES);
		}).getMessage();
		assertEquals(message, "Invalid String , numbers is either alphanumeric or it is improperly delimited");
	}

	@Test
	public void testAddWithNegativeNumbers() throws StringCalculatorException {
		String message = Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_NEGATIVE_NUMBER);
		}).getMessage();

		assertNotNull(message);
		assertEquals("NegativeNumbers :-5-10 Not Allowed", message);
	}

	@Test
	public void testAddWithNullParameter() {
		Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(null);
		});
	}

	@Test
	public void testAddWithEmptyParameter() throws StringCalculatorException {
		assertEquals(calculator.add(" ").intValue(), 0);
	}

	@Test
	public void testAddWithOneParameter() throws StringCalculatorException {
		assertEquals(calculator.add("1").intValue(), 1);
	}

	@Test
	public void testAddWithAlphanumericParameter() {
		String message = Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_ALPHANUMERIC);
		}).getMessage();
		assertEquals(message, "Invalid String , numbers is either alphanumeric or it is improperly delimited");
	}

	@Test
	public void testAddWithSpaceSeparatedParameter() throws StringCalculatorException {
		Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_SPACED);
		});
	}

	@Test
	public void testAddWithLargeNumberAsParameter() throws StringCalculatorException {
		assertEquals(calculator.add(new BigInteger("1234567890123456789012345678901234567890").toString() + ","
				+ new BigInteger("1234567890123456789012345678901234567890").toString()), BigInteger.ZERO);
	}

	@AfterAll
	public static void teardown() {
		calculator = null;
	}

}
