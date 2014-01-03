package com.tadams;

import com.tadams.util.MathUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilTest {
	
	@Test
	public void testLCM() {
		
		assertEquals(150, MathUtil.calcLeastCommonMultiplier(25, 30));
		assertEquals(5, MathUtil.calcLeastCommonMultiplier(5, 5));
		assertEquals(245, MathUtil.calcLeastCommonMultiplier(49, 35));
		assertEquals(16, MathUtil.calcLeastCommonMultiplier(8, 16));
		assertEquals(20, MathUtil.calcLeastCommonMultiplier(10, 20));
		assertEquals(6, MathUtil.calcLeastCommonMultiplier(6, 3));
	}
	
	@Test
	public void format() {
		assertEquals("1,000", MathUtil.format(1000));
	}

}
