package com.kata.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		Integer result = calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED);
		assertNotNull(result);
		assertEquals(TestUtils.expectedResultMap.get("NUMBERS_WITH_COMMA_DELIMITED"), result);
	}
	
	@Test
	public void testAddForEmptyString() throws StringCalculatorException {
		Integer result = calculator.add("");
		assertNotNull(result);
		assertEquals(0, result);
	}

	@Test
	public void testAddWithNonTrimmedSpaceInsideNumbers() throws StringCalculatorException {
		String message = Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_WITHOUT_SPACE_TRIMMED_VALUES);
		}).getMessage();
		assertEquals(message, "Invalid String , numbers is either alphanumeric or it is improperly delimited");
	}

	@Test
	public void testAddWithNullParameter() {
		Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add(null);
		});
	}

	@Test
	public void testAddWithEmptyParameter() {
		String message = Assertions.assertThrows(StringCalculatorException.class, () -> {
			calculator.add("");
		}).getMessage();
		assertEquals(message, "Null or Empty numbers are not allowed");
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
			Integer result = calculator.add(TestUtils.NUMBERS_WITH_COMMA_DELIMITED_SPACED);
		});
	}

	@AfterAll
	public static void teardown() {
		calculator = null;
	}

}
