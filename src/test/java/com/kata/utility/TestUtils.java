package com.kata.utility;

import java.util.HashMap;

public final class TestUtils {

	public static final String NUMBERS_WITH_COMMA_DELIMITED = "1,2,3,4,5,";
	public static final String NUMBERS_WITH_COMMA_AND_NEWLINE_DELIMITED = "1,2\n3,4,\n5,";
	public static final String NUMBERS_WITH_COMMA_DELIMITED_ALPHANUMERIC = "1,2,3,4,5,b,fstrgfd";
	public static final String NUMBERS_WITH_COMMA_DELIMITED_SPACED = "1,2, 3,4 ,5,, , ,";
	public static final String NUMBERS_WITH_COMMA_DELIMITED_WITHOUT_SPACE_TRIMMED_VALUES = "1,2, 3, 4 ,5";
	public static final HashMap<String, Integer> expectedResultMap = new HashMap<>();

	static {
		expectedResultMap.put("NUMBERS_WITH_COMMA_DELIMITED", 15);
	}
}
