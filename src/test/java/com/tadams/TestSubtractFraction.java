package com.tadams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSubtractFraction {
	
	@Test
	public void pos() {
		Fraction f1 = new Fraction(2,5);
		Fraction f2 = new Fraction(4,8);
		Fraction ans = f1.subtract(f2);
		ans.reduce();
		assertEquals(Fraction.valueOf("-1/10"), ans);
	}

}
