package com.sort;

import java.util.Arrays;

/**
 * @author zbs
 * @since 2022/11/26 20:48
 */
public class A2_SelectionSort {
    public static void main(String[] args) {
        int[] arr = {2,0,11,4,3,3,9,21,1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[min] > arr[j]) min = j;
            }
            Util.swap(arr, i, min);
        }
    }
}
