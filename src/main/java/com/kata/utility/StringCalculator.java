package com.kata.utility;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public final class StringCalculator {

	public Integer add(String numbers) {
		final AtomicInteger sum = new AtomicInteger(0);
		
		Arrays.asList(numbers.split(",")).forEach(number -> {
			sum.addAndGet(Integer.parseInt(number));
		});
		
		return sum.get();
	}
}
