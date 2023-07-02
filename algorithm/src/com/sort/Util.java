package com.sort;

/**
 * @author zbs
 * @since 2022/11/25 19:32
 */
public class Util {
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
