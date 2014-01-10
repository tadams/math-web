package com.tadams.util;

import com.tadams.Fraction;

public class HtmlFractionFormatter {

    private static final String EQUAL_NUMERATOR_FORMAT   =  "<sup>%s</sup>";
    private static final String DIFF_NUMERATOR_FORMAT    =  "<sup>%s <del><font color=red>%s</font></del></sup>";

    private static final String EQUAL_DENOMINATOR_FORMAT =  "<sub>%s</sub>";
    private static final String DIFF_DENOMINATOR_FORMAT  =  "<sub><del><font color=red>%s</font></del> %s</sub>";

    private HtmlFractionFormatter() {}

    public static String format(Fraction fraction) {
        return format(fraction, fraction);
    }

    public static String format(Fraction f1, Fraction f2) {

        if (isZero(f1.getNumerator())) {
            return format(f1.getWholeNumber());
        }

        String numerator = format(f1.getNumerator(), f2.getNumerator(),
                EQUAL_NUMERATOR_FORMAT, DIFF_NUMERATOR_FORMAT);

        String denominator = format(f1.getDenominator(), f2.getDenominator(),
                EQUAL_DENOMINATOR_FORMAT, DIFF_DENOMINATOR_FORMAT);

        String wholeNumber = isZero(f1.getWholeNumber()) ? "" : " " + format(f1.getWholeNumber());

        return wholeNumber + numerator + "&frasl;" + denominator;
    }


    private static String format(int i1, int i2, String equalFormat, String diffFormat) {

        String formatString = i1 == i2 ? equalFormat : diffFormat;

        return String.format(formatString, format(i1), format(i2));
    }

    public static String format(int integer) {
        return String.format("%1$,d", integer);
    }

    private static boolean isZero(int num) {
        return num == 0;
    }

}
