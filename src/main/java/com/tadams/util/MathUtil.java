package com.tadams.util;

public class MathUtil {
	
	public static int calcLeastCommonMultiplier(int i1, int i2) {
		return (i1 * i2) / calcGreatestCommonDivisor(i1, i2);
	}
	
	public static int calcGreatestCommonDivisor(int i1, int i2) {
		
		i1 = Math.abs(i1);
		i2 = Math.abs(i2);
		
		return calcGreatestCommonDenominator(i1, i2);
	}
	
	private static int calcGreatestCommonDenominator(int i1, int i2) {
		
		if (i1 == i2) {
			return i1;
		}
		
		if (i1 > i2) {
			return calcGreatestCommonDenominator(i2, i1 - i2);
		}
		
		if (i1 < i2) {
			return calcGreatestCommonDenominator(i1, i2 - i1);
		}
		
		throw new AssertionError();
	}
	
	public static int calcGreatestCommonFactor(int a, int b) {

		int minABS = Math.min(Math.abs(a), Math.abs(b));
		for (int i = minABS; i > 0; i--){
			if ((a % i == 0) && (b % i == 0)){
				return i;
			}
		}
		return 1;
	}
	
	public static String format(int integer) {
		return String.format("%1$,d", integer);
	}

}
