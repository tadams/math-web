package com.tadams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class TestFractionImproper {
	
	@Test()
    @Ignore
	public void mixedNum() {
		Fraction f1 = Fraction.valueOf("2 1/2");
		assertTrue(f1.simplifyMixedFraction());
		assertEquals("5/2",f1.toString());
	}

}
