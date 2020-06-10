package com.kata.utility;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
	
	@AfterAll
	public static void teardown() {
		calculator = null;
	}

}
