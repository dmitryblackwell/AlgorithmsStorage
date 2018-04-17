package com.blackwell.arraysorts;

import javafx.util.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Demo {
    private static final int ARR_SIZE=10_000;
    private static final int BOUND = 1000;

    private static final String[] SortsName = {"Bubble", "Comb", "Shell", "Insertion", "Quick", "Selection", "Shaker", "Tree", "Gnome"};

    private static void FeelRandom(int[] arr){
        Random R = new Random();
        for(int i =0;i<arr.length;++i)
            arr[i] = R.nextInt(BOUND);
    }

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        String[][] results = new String[3][];



        // TODO calling all sorts instantly and saving there time running
//        BubbleSort.main(arr);
//        CombSort.main(arr);
//        ShellSort.main(arr);
//        InsertionSort.main(arr);
//        QuickSort.main(arr);
//        SelectionSort.main(arr);
//        ShakerSort.main(arr);
//        TreeSort.main(arr);
//        GnomeSort.main(arr);

        // TODO draw table with result in right order
        /*
        final Object[][] table = new String[4][];
        table[0] = new String[] { "foo", "bar", "baz" };
        table[1] = new String[] { "bar2", "foo2", "baz2" };
        table[2] = new String[] { "baz3", "bar3", "foo3" };
        table[3] = new String[] { "foo4", "bar4", "baz4" };

        for (final Object[] row : table) {
            System.out.format("%15s%15s%15s\n", row);
        }
         */
    }
}
