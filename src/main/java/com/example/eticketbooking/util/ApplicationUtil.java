package com.example.eticketbooking.util;

import java.util.HashSet;
import java.util.List;

public class ApplicationUtil {
    public static <T>  boolean isTwoListsEqual(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            return false;
        }
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
