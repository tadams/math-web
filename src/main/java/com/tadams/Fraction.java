package com.tadams;

import com.tadams.util.MathUtil;

public class Fraction {
	
	private int wholeNumber;
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
		this(f.getWholeNumber(), f.getNumerator(), f.getDenominator());
	}
	
	public Fraction(int numerator, int denominator) {
		this(0, numerator, denominator);
	}
	
	public Fraction(int wholeNumber, int numerator, int denominator) {
		
		if (denominator == 0) {
			throw new IllegalArgumentException("denominator cannot = 0");
		}
		
		this.wholeNumber = wholeNumber;
		this.numerator 		= numerator;
		this.denominator 	= denominator;
	}
	
	public boolean applyLeastCommonDenominator(Fraction other) {
		
		if (denominator == other.getDenominator()) {
			return false;
		}

		int leastCommonMultiplier = MathUtil.calcLeastCommonMultiplier(denominator,
                                                                       other.getDenominator());

        boolean isF1Applied = applyLeastCommonDenominator(leastCommonMultiplier);
        boolean isF2Applied = other.applyLeastCommonDenominator(leastCommonMultiplier);

        return isF1Applied || isF2Applied;
	}
	
	public boolean applyLeastCommonDenominator(int leastCommonMultiplier) {
		if (denominator == leastCommonMultiplier) {
			return false;
		}
		
		int factor  = leastCommonMultiplier / denominator;
		numerator   = factor * numerator;
		denominator = leastCommonMultiplier;
		return true;
	}
	
	public boolean simplifyMixedFraction() {

		if (wholeNumber != 0) {
            convertToImproperFraction();
			return true;
		}
		return false;
	}

    private void convertToImproperFraction() {

        numerator = (Math.abs(wholeNumber) * denominator) + numerator;
        if (wholeNumber < 0) {
            numerator = numerator * -1;
        }
        wholeNumber = 0;
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
			wholeNumber = wholeNumber + numerator / denominator;
			numerator = Math.abs(numerator % denominator);
			reduced = true;
		}
		
		int gcf = MathUtil.calcGreatestCommonFactor(numerator, denominator);
		if (gcf != 1) {
			numerator 		= numerator / gcf;
			denominator 	= denominator / gcf;
			reduced = true;
		}
		
		return reduced;
	}

	public Fraction add(Fraction other) {

		if (other.getDenominator() != denominator) {
			applyLeastCommonDenominator(other);
		}

		if (getSign() != other.getSign()	&&
		   (getSignNumerator() + other.getSignNumerator() < 0)) {
			borrow();
		}

		return new Fraction(wholeNumber + other.getWholeNumber(),
				getSignNumerator() + other.getSignNumerator(),
    			denominator);
	}

	public void borrow() {
		if (wholeNumber > 0) {
			wholeNumber--;
			numerator = numerator + denominator;
		}
	}

	public Fraction reciprocal() {
		simplifyMixedFraction();
		if (getSign() == Sign.NEG) {
			denominator = denominator * -1;
			numerator = numerator * -1;
		}

		return new Fraction(denominator, numerator);
	}

	public Sign getSign() {

		if (wholeNumber == 0) {
			return numerator < 0 ? Sign.NEG : Sign.POS;
		}
		return wholeNumber < 0 ? Sign.NEG : Sign.POS;
	}

	public int getSignNumerator() {
		if (wholeNumber < 0) {
			return numerator * -1;
		}
		return numerator;
	}

	public Fraction subtract(Fraction other) {

		if (other.wholeNumber > 0) {
			other.wholeNumber = other.wholeNumber * -1;
		} else {
			other.numerator = other.numerator * -1;
		}
		return add(other);
	}

	public Fraction multiply(Fraction other) {
		simplifyMixedFraction();
		other.simplifyMixedFraction();

		return new Fraction(numerator   * other.getNumerator(),
				            denominator * other.getDenominator());
	}

	public Fraction divide(Fraction other) {
		simplifyMixedFraction();
		other.simplifyMixedFraction();

		return new Fraction(numerator   * other.getDenominator(),
				            denominator * other.getNumerator());
	}

	public boolean equals(Object other) {
		if (other instanceof Fraction) {
			Fraction oFrac = (Fraction)other;

			if (wholeNumber != oFrac.getWholeNumber()) {
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
			return MathUtil.format(wholeNumber);
		}

		String frac = "<sup>" + MathUtil.format(numerator)   + "</sup>" +
		  "&frasl;" + "<sub>" + MathUtil.format(denominator) + "</sub>";

		if (wholeNumber == 0) {
			return frac;
		} else {
			return MathUtil.format(wholeNumber) + " " + frac;
		}
	}

	public String toString(Fraction other) {

		String frac = formatDiff("sup", numerator, other.getNumerator()) +
					  "&frasl;" +
					  formatDiff("sub", denominator, other.getDenominator());

		if (wholeNumber == 0) {
			return frac;
		} else {
			return MathUtil.format(wholeNumber) + " " + frac;
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

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getWholeNumber() {
        return wholeNumber;
    }

}
