package com.tadams;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FractionImproperTest {
	
	@Test()
	public void mixedNum() {
		Fraction f1 = Fraction.valueOf("2 1/2");
		assertTrue(f1.simplifyMixedFraction());
		assertEquals(new Fraction(5, 2), f1);
	}

}
