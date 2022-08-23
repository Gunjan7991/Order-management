package com.demo.order_management.ordermanagement.junitsample;

import org.springframework.stereotype.Component;

@Component
public class Calculation {
	public int findSum(int a, int b) {
		return a+b;
	}

	public int findDiff(int a, int b) {
		return a - b;
	}

	public int findMul(int a, int b) {
		return a * b;
	}

	public int findDiv(int a, int b) {
		return a / b;
	}
	
	public int findRemainder(int a, int b) {
		return a%b;
	}
	
	public int findFactorial(int a) {
		int total= 1;
		while(a!=1) {
			total = total*a;
			a= a-1;
		}
		return total;
	}
}
