package com.tadams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class TestAddFraction {
	
	@Test
	public void simple_add() {
		Fraction f1 = new Fraction(1,2);
		Fraction f2 = new Fraction(1,2);
		Fraction answer = f1.add(f2);
		answer.reduce();
		assertEquals("1", answer.toString());
	}
	
	@Test
	public void add_neg() {
		Fraction f1 = new Fraction(-1,2);
		Fraction f2 = new Fraction(1,2);
		Fraction fa = f1.add(f2);
		
		assertEquals("0", fa.toString());
	}
	
	@Test
	public void mixedAdd() {
		Fraction f1 = new Fraction(3,1,3);
		Fraction f2 = new Fraction(4,1,3);
		Fraction ans = f1.add(f2);
		ans.reduce();
		assertEquals(Fraction.valueOf("7 2/3"), ans);
	}
	
	@Test
	public void add_NegativeNum() {
		Fraction f1 = new Fraction(-3,1,3);
		Fraction f2 = new Fraction(4,1,3);
		Fraction ans = f1.add(f2);
		ans.reduce();
		assertEquals(new Fraction(1,0,1), ans);
	}
	
	@Test
	public void reduce() {
		Fraction f1 = new Fraction(-10,5);
		f1.reduce();
		assertEquals(Fraction.valueOf("-2"),f1);
	}
	
	@Test
	public void equal_Test() {
		Fraction f1 = new Fraction(5,3,7);
		Fraction f2 = new Fraction(3,7);
		assertFalse(f1.equals(f2));
	}
	

}
