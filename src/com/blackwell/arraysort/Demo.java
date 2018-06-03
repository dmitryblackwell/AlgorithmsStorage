package com.blackwell.arraysort;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Demo {
    private static final int ARR_SIZE = 500_000;
    private static final int BOUND = 1_000_000;

    private static final String ClassPath = "com.blackwell.arraysort.";
    private static final String[] SortsName = {"Bubble", "Comb", "Shell", "Insertion", "Quick", "Selection",
            "Shaker", "Tree", "Gnome", "Merge", "Heap", "Counting", "Radix", "Bucket", "Bitonic", "LSD", "MSD"};


//    private static final String[] SortsName = {"Comb", "Shell", "Quick",
//            "Tree", "Merge", "Heap", "Radix", "Bucket", "Bitonic", "LSD", "MSD"};


//    private static final String[] SortsName = {"Shell", "Quick", "Merge", "Radix", "LSD", "MSD"};


//    private static final String[] SortsName = {"LSD", "MSD"};

    private static void FeelRandom(int[] arr){
        Random R = new Random();
        for(int i =0;i<arr.length;++i)
            arr[i] = R.nextInt(BOUND);
    }

    private static void PairsSort(Pair[] p){
        Pair tmp;
        for (int i = 0; i < p.length; ++i) {
            for (int j = 1; j < p.length - i; ++j) {
                long k1 = (long) p[j - 1].getValue();
                long k2 = (long) p[j].getValue();
                if (k1 < k2) {
                    tmp = p[j-1];
                    p[j-1] = p[j];
                    p[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        Pair[] sorts = new Pair[SortsName.length];

        int[] startArray = new int[ARR_SIZE];
        FeelRandom(startArray);
        try {
            for(int i=0; i<SortsName.length; ++i) {
                System.arraycopy(startArray,0,arr,0,ARR_SIZE);

                Class<?> clazz = Class.forName(ClassPath + SortsName[i]);
                Method m = clazz.getMethod("sort", int[].class);
                long RunTime = (long) m.invoke(null, (Object) arr);
                sorts[i] = new Pair<>(SortsName[i], RunTime);

                //System.out.println(Arrays.toString(arr));
                System.out.println(SortsName[i] + " passed");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No Such Method Exception");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Illegal Access Exception");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Invocation Target Exception");
            e.printStackTrace();
        }
        PairsSort(sorts);

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        for (Pair p : sorts) {
            String time = df.format((long) p.getValue() * Math.pow(10,-9)) + " secs";
            System.out.format("%15s%15s\n",p.getKey(),time);
        }
    }
}