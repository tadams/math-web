package com.tadams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplyTest {
	
	@Test
	public void simple() {
		Fraction f1 = new Fraction(2,3);
		Fraction f2 = new Fraction(4,5);
		Fraction ans = f1.multiply(f2);
		ans.reduce();
		assertEquals(new Fraction(8,15),ans);
	}
	
	@Test
	public void int_multi_frac() {
		assertEquals(Fraction.valueOf("2 1/2"), 
				     multiply("5","1/2"));
		assertEquals(Fraction.valueOf("6 3/4"), 
			     multiply("9","3/4"));
		assertEquals(Fraction.valueOf("3/7"), 
			     multiply("9/15","5/7"));
	}
	
	@Test
	public void negative_num() {
		assertEquals(Fraction.valueOf("-2 2/5"), 
			     multiply("6","-2/5"));
	}
	
	private Fraction multiply(String s1, String s2) {
		Fraction f1 = Fraction.valueOf(s1);
		Fraction f2 = Fraction.valueOf(s2);
		Fraction ans = f1.multiply(f2);
		ans.reduce();
		return ans;
	}

}
