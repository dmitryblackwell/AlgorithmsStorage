package com.blackwell.arraysorts;

import java.util.Random;

public class Demo {
    private static final int ARR_SIZE=1000;

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        Random R = new Random();
        for(int i =0;i<ARR_SIZE;++i)
            arr[i] = R.nextInt(1000);

        BubbleSort.main(arr);   //7
        CombSort.main(arr);   //5
        ShellSort.main(arr);   //3
        InsertionSort.main(arr);
        QuickSort.main(arr);   //4
        SelectionSort.main(arr);   //6
        ShakerSort.main(arr);
        TreeSort.main(arr);
    }
}
