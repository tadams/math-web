package com.tadams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FractionTest {

    @Test
    public void shouldParseFraction() {
        Fraction fraction = Fraction.valueOf("1/2");
        assertEquals(0, fraction.getWholeNumber());
        assertEquals(1, fraction.getNumerator());
        assertEquals(2, fraction.getDenominator());
    }

    @Test
    public void shouldParseMixedFraction() {
        Fraction fraction = Fraction.valueOf("2 1/2");
        assertEquals(2, fraction.getWholeNumber());
        assertEquals(1, fraction.getNumerator());
        assertEquals(2, fraction.getDenominator());
    }

    @Test
    public void shouldParseWholeNumberFraction() {
        Fraction fraction = Fraction.valueOf("3");
        assertEquals(3, fraction.getWholeNumber());
        assertEquals(0, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    public void shouldApplyLeastCommonDenominator_OneFraction() {
        Fraction fraction = Fraction.valueOf("4/8");
        Fraction expected = Fraction.valueOf("8/16");
        assertTrue(fraction.applyLeastCommonDenominator(16));
        assertEquals(expected, fraction);
    }

    @Test
    public void shouldNotApplyLeastCommonDenominator_OneFraction() {
        Fraction fraction = Fraction.valueOf("4/8");
        assertFalse(fraction.applyLeastCommonDenominator(8));
    }

    @Test
    public void shouldApplyLeastCommonDenominator_TwoFractions() {
        Fraction f1 = Fraction.valueOf("5/7");
        Fraction f2 = Fraction.valueOf("1/4");
        boolean applied = f1.applyLeastCommonDenominator(f2);

        assertTrue(applied);
        assertEquals(Fraction.valueOf("20/28"), f1);
        assertEquals(Fraction.valueOf("7/28"), f2);
    }

    @Test
    public void shouldNotApplyLeastCommonDenominator_TwoFractions() {
        Fraction f1 = Fraction.valueOf("1/6");
        Fraction f2 = Fraction.valueOf("5/6");
        boolean applied = f1.applyLeastCommonDenominator(f2);
        assertFalse(applied);

        applied = f2.applyLeastCommonDenominator(f1);
        assertFalse(applied);

        assertEquals(Fraction.valueOf("1/6"), f1);
        assertEquals(Fraction.valueOf("5/6"), f2);
    }

    @Test
    public void shouldConvertMixedFractionToImproperFraction() {
        Fraction f = Fraction.valueOf("2 4/6");

        boolean applied = f.simplifyMixedFraction();

        assertTrue(applied);
        assertEquals(Fraction.valueOf("16/6"), f);
    }

    @Test
    public void shouldConvertMixedFractionToImproperFraction_WithNegativeNumber() {
        Fraction f = Fraction.valueOf("-2 4/6");

        boolean applied = f.simplifyMixedFraction();

        assertTrue(applied);
        assertEquals(Fraction.valueOf("-16/6"), f);
    }

}
