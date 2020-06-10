package com.kata.utility;

import java.util.HashMap;

public final class TestUtils {

	public static final String NUMBERS_WITH_COMMA_DELIMITED = "1,2,3,4,5";
	public static final HashMap<String, Integer> expectedResultMap = new HashMap<>();

	
	static {
		expectedResultMap.put("NUMBERS_WITH_COMMA_DELIMITED", 15);
	}
}
