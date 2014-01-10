package com.tadams.util;

import com.tadams.Fraction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlFractionFormatterTest {

    @Test
    public void shouldFormatFractionWithDifferences() {
        Fraction f1 = new Fraction(12, 9);
        Fraction f2 = new Fraction(12, 3);

        String formattedFraction = HtmlFractionFormatter.format(f1, f2);
        assertEquals("<sup>12</sup>&frasl;<sub><del><font color=red>9</font></del> 3</sub>",
                     formattedFraction);
    }

}
