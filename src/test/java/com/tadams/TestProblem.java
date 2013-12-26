package com.tadams;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class TestProblem {
	
	@Test
	public void addFraction() {
		Problem prob = new Problem("2 1/2 + 1/2");
		System.out.println(prob.getSteps());
		assertEquals("3",prob.getAnswer().toString());
	}
	
	@Test
    @Ignore
	public void addLcdFraction() {
		Problem prob = new Problem("3 1/14 + 1 2/7");
		assertEquals("4 5/14",prob.getAnswer().toString());
		System.out.println(prob.getSteps());
	}
	
	@Test
    @Ignore
	public void muti_neg() {
		Problem prob = new Problem("9/14 * -7/9");
		assertEquals("-1/2",prob.getAnswer().toString());
		assertEquals(Fraction.valueOf("-1/2"),prob.getAnswer());
		System.out.println(prob.getSteps());
	}
	
	@Test
	public void homeWork() {
		doTest("2 4/8 + 1 2/3","4 1/6");
		doTest("6 - 4 7/8","1 1/8");
		doTest("6/5 + 10/12","2 1/30");
		doTest("12 1/7 - 8 2/3","3 10/21");
		doTest("1/7 * 2/9","2/63");
		doTest("3 * 4 1/4","12 3/4");
		doTest("3 1/2 * 4 1/4","14 7/8");
	}
	
	@Test
	public void carry() {
		doTest("3 1/4 - 2 1/2","3/4");
	}
	
	@Test
    @Ignore
	public void divide() {
		doTest("2 4/8 / 1 2/3","1 1/2");
		doTest("2 / 1/2","4");
		doTest("-2 / 1/2","-4");
		doTest("2 / -1/2","-4");
	}
	
	@Test
	public void num5() {
		doTest("5/6 + 10/12","1 2/3");
	}
	
	private void doTest(String prob, String answer) {
		Problem problem = new Problem(prob);
		System.out.println(problem.getSteps());
		assertEquals(Fraction.valueOf(answer),problem.getAnswer());
	}

}
