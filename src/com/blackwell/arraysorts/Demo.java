package com.blackwell.arraysorts;

import java.util.Random;

public class Demo {
    private static final int ARR_SIZE=10_000;

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        Random R = new Random();
        for(int i =0;i<ARR_SIZE;++i)
            arr[i] = R.nextInt(1000);
        BubbleSort.main(arr);
        CombSort.main(arr);
        InsertionSort.main(arr);
        QuickSort.main(arr);
        SelectionSort.main(arr);
        ShakerSort.main(arr);
    }
}
