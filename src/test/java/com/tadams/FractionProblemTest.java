package com.tadams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractionProblemTest {

    @Test
    public void shouldDetermineTypeOfOperation() {
        FractionProblem problem = new FractionProblem("1/2 * 1/3");
        assertEquals(problem.operation, Operation.MULTIPLY);
    }
	
	@Test
	public void addFraction() {
		doTest("2 1/2 + 1/2", "3");
	}
	
	@Test
	public void addLcdFraction() {
		doTest("3 1/14 + 1 2/7", "4 5/14");
	}
	
	@Test
	public void muti_neg() {
		doTest("9/14 * -7/9", "-1/2");
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
		FractionProblem fractionProblem = new FractionProblem(prob);
		System.out.println(fractionProblem.getSteps());
		assertEquals(Fraction.valueOf(answer), fractionProblem.getAnswer());
	}

}
