package com.blackwell.arraysorts;

import java.util.Random;

public class Demo {
    // TODO change time showing in seconds and ms
    // TODO change args type form String[] to int[] or <T>

    public static void main(String[] args) {
        String[] arrayStr={""};
        Random R = new Random();
        for(int i =0;i<1000;++i)
            arrayStr[0]+= String.valueOf(R.nextInt(1000)) + " ";

        BubbleSort.main(arrayStr);
        //CombSort
        InsertionSort.main(arrayStr);
        QuickSort.main(arrayStr);
        SelectionSort.main(arrayStr);
        ShakerSort.main(arrayStr);
    }
}
