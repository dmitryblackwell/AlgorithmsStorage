package com.blackwell.arraysorts;

import java.util.Arrays;

public class BubbleSort { // снипицы
    public static int[] main(String[] args) {
        int[] arr = Array.toIntArray(args);
        int tmp = 0;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 1; j < arr.length - i; ++j) {
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        return arr;
    }
}
