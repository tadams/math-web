package com.tadams;

import static org.junit.Assert.assertEquals;

import com.tadams.util.MathUtil;
import org.junit.Test;

public class TestMathUtil {
	
	@Test
	public void testLCM() {
		
		assertEquals(150, MathUtil.leastComMulti(25, 30));
		assertEquals(5, MathUtil.leastComMulti(5, 5));
		assertEquals(245, MathUtil.leastComMulti(49, 35));
		assertEquals(16, MathUtil.leastComMulti(8, 16));
		assertEquals(20, MathUtil.leastComMulti(10, 20));
		assertEquals(6, MathUtil.leastComMulti(6, 3));
	}
	
	@Test
	public void format() {
		assertEquals("1,000", MathUtil.format(1000));
	}

}
