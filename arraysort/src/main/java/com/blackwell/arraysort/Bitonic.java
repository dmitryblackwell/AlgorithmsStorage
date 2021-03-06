package com.blackwell.arraysort;

public class Bitonic {
    /* The parameter dir indicates the sorting direction,
       ASCENDING or DESCENDING; if (a[i] > a[j]) agrees
       with the direction, then a[i] and a[j] are
       interchanged. */
    private static void compAndSwap(int a[], int i, int j, int dir) {
        if ( (a[i] > a[j] && dir == 1) ||
                (a[i] < a[j] && dir == 0)) {
            // Swapping elements
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    /* It recursively sorts a bitonic sequence in ascending
       order, if dir = 1, and in descending order otherwise
       (means dir=0). The sequence to be sorted starts at
       index position low, the parameter cnt is the number
       of elements to be sorted.*/
    private static void bitonicMerge(int a[], int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, dir);
            bitonicMerge(a,low, k, dir);
            bitonicMerge(a,low+k, k, dir);
        }
    }

    /* This funcion first produces a bitonic sequence by
       recursively sorting its two halves in opposite sorting
       orders, and then  calls bitonicMerge to make them in
       the same order */
    private static void bitonicSort(int a[], int low, int cnt, int dir) {
        if (cnt>1) {
            int k = cnt/2;

            // sort in ascending order since dir here is 1
            bitonicSort(a, low, k, 1);

            // sort in descending order since dir here is 0
            bitonicSort(a,low+k, k, 0);

            // Will merge wole sequence in ascending order
            // since dir=1.
            bitonicMerge(a, low, cnt, dir);
        }
    }

    /*Caller of bitonicSort for sorting the entire array
      of length N in ASCENDING order */
    void sort(int a[], int N, int up) {
        bitonicSort(a, 0, N, up);
    }
    public static long sort(int[] arr){
        long StartTime = System.nanoTime();

        bitonicSort(arr, 0, arr.length, 1);

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }

}
