package com.blackwell.arraysorts;

public class Array {
    public static int[] toIntArray(String[] args){
        String[] strArray = args[0].split(" ");
        int[] arr = new int[strArray.length];
        for(int i = 0;i< strArray.length; ++i){
            arr[i] =Integer.parseInt(strArray[i]);
        }
        return arr;
    }
}
