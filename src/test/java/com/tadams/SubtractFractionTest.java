package com.tadams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubtractFractionTest {
	
	@Test
	public void pos() {
		Fraction f1 = new Fraction(2,5);
		Fraction f2 = new Fraction(4,8);
		Fraction ans = f1.subtract(f2);
		ans.reduce();
		assertEquals(Fraction.valueOf("-1/10"), ans);
	}

}
