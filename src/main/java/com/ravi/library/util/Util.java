package com.ravi.library.util;

import java.util.List;

public final class Util {

    public static <T> boolean isNoValue(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isHaveValue(List<T> list) {
        return !isNoValue(list);
    }

    public static boolean isNoValue(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isHaveValue(String str) {
        return !isNoValue(str);
    }

}
