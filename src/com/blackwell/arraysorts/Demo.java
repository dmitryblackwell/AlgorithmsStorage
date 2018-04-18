package com.blackwell.arraysorts;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Demo {
    private static final int ARR_SIZE = 10_000;
    private static final int BOUND = 1000;

    private static final String ClassPath = "com.blackwell.arraysorts.";
    private static final String[] SortsName = {"Bubble", "Comb", "Shell", "Insertion", "Quick", "Selection", "Shaker", "Tree", "Gnome"};

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
                if (k1 > k2) {
//                    tmp = new Pair(p[j - 1].getKey(), p[j-1].getValue());
//                    p[j - 1] = new Pair(p[j].getKey(), p[j].getValue());
//                    p[j] = new Pair(tmp.getKey(),tmp.getValue());
                    tmp = p[j-1];
                    p[j-1] = p[j];
                    p[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        Pair<String, Long>[] sorts = new Pair[SortsName.length];

        // TODO fix this warnings
        try {
            for(int i=0; i<SortsName.length; ++i) {
                FeelRandom(arr);
                Class clazz = Class.forName(ClassPath + SortsName[i]);
                Method m = clazz.getMethod("sort", int[].class);
                long RunTime = (long) m.invoke(null,arr);
                sorts[i] = new Pair<>(SortsName[i], RunTime);
                // System.out.println(Arrays.toString(arr));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        PairsSort(sorts);

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        for (Pair p : sorts) {
            System.out.println(p.getKey() +": "+ df.format((long) p.getValue() * Math.pow(10,-9)));
        }

    }
}
