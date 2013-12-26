package com.tadams;

import com.tadams.util.MathUtil;

public class Fraction {
	
	private int     integer;
	private int		numerator;
	private int 	denominator;
	
	public static Fraction valueOf(String str) {
		String[] parts = str.trim().split(" |/");
		int integer			= 0;
		int numerator 		= 0;
		int denominator 	= 1;
		
		switch (parts.length) {
			case 1 : 
				integer = Integer.parseInt(parts[0]);
				break;
			case 2 :
				numerator = Integer.parseInt(parts[0]);
				denominator = Integer.parseInt(parts[1]);
				break;
			case 3:
				integer = Integer.parseInt(parts[0]);
				numerator = Integer.parseInt(parts[1]);
				denominator = Integer.parseInt(parts[2]);
				break;
			default:
				throw new IllegalArgumentException();
		}
		return new Fraction(integer,
							numerator,
							denominator);
	}
	
	public Fraction(Fraction f) {
		this(f.getInteger(), f.getNumerator(), f.getDenominator());
	}
	
	public Fraction(int numerator, int denominator) {
		this(0, numerator, denominator);
	}
	
	public Fraction(int integer, int numerator, int denominator) {
		
		if (denominator == 0) {
			throw new IllegalArgumentException("denominator cannot = 0");
		}
		
		this.integer        = integer;
		this.numerator 		= numerator;
		this.denominator 	= denominator;
	}
	
	public boolean applyLeastComDenom(Fraction other) {
		
		if (denominator == other.getDenominator()) {
			return false;
		}

		int lcd = MathUtil.leastComMulti(denominator, other.getDenominator());
		return applyLeastComDenom(lcd) | other.applyLeastComDenom(lcd);
	}
	
	public boolean applyLeastComDenom(int lcd) {
		if (denominator == lcd) {
			return false;
		}
		
		int factor = lcd / denominator;
		numerator = factor * numerator;
		denominator = lcd;
		return true;
	}
	
	public boolean improperFraction() {
		if (integer != 0) {
			numerator = (Math.abs(integer) * denominator) + numerator;
			if (integer < 0) {
				numerator = numerator * -1;
			}
			integer = 0;
			return true;
		}
		return false;
	}
	
	public void applyFactorNumerator(int factor) {
		numerator = numerator / factor;
	}
	
	public void applyFactorDenominator(int factor) {
		denominator = denominator / factor;
	}
	
	public boolean reduce() {
		boolean reduced = false;
		if (Math.abs(numerator) >= denominator) {
			integer = integer + numerator / denominator;
			numerator = Math.abs(numerator % denominator);
			reduced = true;
		}
		
		int gcf = MathUtil.calcGCF(numerator, denominator);
		if (gcf != 1) {
			numerator 		= numerator / gcf;
			denominator 	= denominator / gcf;
			reduced = true;
		}
		
		return reduced;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}
	
	public int getInteger() {
		return integer;
	}
	
	public Fraction add(Fraction other) {
			
		if (other.getDenominator() != denominator) {
			applyLeastComDenom(other);
		}
		
		if (getSign() != other.getSign()	&&
		   (getSignNumerator() + other.getSignNumerator() < 0)) {
			borrow();
		}
		
		return new Fraction(integer + other.getInteger(),
				getSignNumerator() + other.getSignNumerator(), 
    			denominator);
	}
	
	public void borrow() {
		if (integer > 0) {
			integer--;
			numerator = numerator + denominator;
		}
	}
	
	public Fraction reciprocal() {
		improperFraction();
		if (getSign() == Sign.NEG) {
			denominator = denominator * -1;
			numerator = numerator * -1;
		}
		
		return new Fraction(denominator, numerator);
	}
	
	public Sign getSign() {
		
		if (integer == 0) {
			return numerator < 0 ? Sign.NEG : Sign.POS;
		}
		return integer < 0 ? Sign.NEG : Sign.POS;
	}
	
	public int getSignNumerator() {
		if (integer < 0) {
			return numerator * -1;
		}
		return numerator;
	}
	
	public Fraction subtract(Fraction other) {
		
		if (other.integer > 0) {
			other.integer = other.integer * -1;
		} else {
			other.numerator = other.numerator * -1;
		}
		return add(other);
	}
	
	public Fraction multiply(Fraction other) {
		improperFraction();
		other.improperFraction();
		
		return new Fraction(numerator   * other.getNumerator(),
				            denominator * other.getDenominator());
	}
	
	public Fraction divide(Fraction other) {
		improperFraction();
		other.improperFraction();
		
		return new Fraction(numerator   * other.getDenominator(),
				            denominator * other.getNumerator());
	}
	
	public boolean equals(Object other) {
		if (other instanceof Fraction) {
			Fraction oFrac = (Fraction)other;
			
			if (integer != oFrac.getInteger()) {
				return false;
			}
			
			if (oFrac.numerator == numerator) {
				if (numerator == 0) {
					return true;
				}
				return oFrac.denominator   == denominator;
			}
		}
		return false;
	}
	
	public String toString() {
		
		if (numerator == 0) {
			return MathUtil.format(integer);
		}
		
		String frac = "<sup>" + MathUtil.format(numerator)   + "</sup>" +
		  "&frasl;" + "<sub>" + MathUtil.format(denominator) + "</sub>";
		
		if (integer == 0) {
			return frac;
		} else {
			return MathUtil.format(integer) + " " + frac;
		}
	}
	
	public String toString(Fraction other) {
		
		String frac = formatDiff("sup", numerator, other.getNumerator()) +
					  "&frasl;" +
					  formatDiff("sub", denominator, other.getDenominator());
		
		if (integer == 0) {
			return frac;
		} else {
			return MathUtil.format(integer) + " " + frac;
		}
	}
	
	private String formatDiff(String tag, int i1, int i2) {
		if (i1 == i2) {
			return String.format("<%s>%s</%s>", tag, MathUtil.format(i1), tag);
		}
		if (tag.equals("sup")) {
			return String.format("<%s>%s <del><font color=red>%s</font></del></%s>", 
					tag, MathUtil.format(i1), MathUtil.format(i2), tag);
		} else {
			return String.format("<%s><del><font color=red>%s</font></del> %s</%s>", 
					tag, MathUtil.format(i2), MathUtil.format(i1), tag);
		}
	}

}
