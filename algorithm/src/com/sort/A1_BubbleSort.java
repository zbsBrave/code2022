package com.sort;

import java.util.Arrays;

/**
 * @author zbs
 * @since 2022/11/25 19:27
 */
public class A1_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {2,9,0,7,1,32,2,7,7};
        bubbleSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort1(int[] arr){
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    Util.swap(arr, j, j+1);
                }
            }
        }
    }

    //优化：当遍历一遍没有发生交换时，其实已经排好序了
    public static void bubbleSort2(int[] arr){
        for(int i = arr.length - 1; i > 0; i--){
            boolean flag = true;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    Util.swap(arr, j, j+1);
                    flag = false;
                }
            }
            //未发生交换，直接返回
            if(flag) return;;
        }
    }


}
