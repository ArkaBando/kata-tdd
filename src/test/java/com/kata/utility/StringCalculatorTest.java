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

	@AfterAll
	public static void teardown() {
		calculator = null;
	}

}
