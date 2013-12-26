package com.tadams.util;

public class Utils {

    // You can't new me
    private Utils() {}

    @SafeVarargs
    public static <T> T firstNonNull(T object, T... objects) {
        if (isNull(object) && (isNull(objects) || objects.length == 0)) {
            throw new IllegalArgumentException("All Parameters Null");
        }

        if (isNotNull(object)) {
            return object;
        }


        for (T obj : objects) {
            if (isNotNull(obj)) {
                return obj;
            }
        }
        throw new IllegalArgumentException("All Parameters Null");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }
}
