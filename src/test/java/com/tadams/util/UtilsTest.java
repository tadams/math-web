package com.tadams.util;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.tadams.util.Utils.firstNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void shouldIdentifyAsNull() {
        assertTrue(Utils.isNull(null));
    }

    @Test
    public void shouldIdentifyAsNotNull() {
        assertTrue(Utils.isNotNull(1L));
        assertTrue(Utils.isNotNull(""));
    }

    @Test
    public void shouldIdentifyAsNotNullFalse() {
        String nullString = null;
        assertFalse(Utils.isNotNull(nullString));
        assertTrue(Utils.isNotNull(1L));
        assertFalse(Utils.isNotNull(null));
    }

    @Test
    public void shouldReturnFirstNonNull() {
        assertEquals(firstNonNull(null, "Foo"), "Foo");
    }

    @Test( expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOneArgumentsIsNull() {
        firstNonNull(null);
    }

    @Test( expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAllArgumentsAreNull() {
        firstNonNull(null, null, null);
    }

    @Test( expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArgumentsAreNullAndEmptyArray() {
        firstNonNull(null, new Object[]{});
    }

}
