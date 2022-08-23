package com.demo.order_management.ordermanagement.junitsample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculationTest {

	Calculation calculation = new Calculation();

	@Test
	public void findSumTest() {
		int sum = calculation.findSum(5, 3);
		assertEquals(8, sum);

	}

	@Test
	public void findDiffTest() {
		int diff = calculation.findDiff(5, 3);
		assertEquals(2, diff);
	}

	@Test
	public void findMulTest() {
		int mult = calculation.findMul(5, 3);
		assertEquals(15, mult);
	}
	
	@Test
	public void findDivTest() {
		int div = calculation.findDiv(15, 5);
		assertEquals(3, div);
	}

	@Test
	public void findRemainderTest() {
		int rem = calculation.findRemainder(10, 10);
		assertEquals(0, rem);
	}
	
	@Test
	public void findFactorialTest() {
		int fack = calculation.findFactorial(5);
		assertEquals(120, fack);
	}
}
