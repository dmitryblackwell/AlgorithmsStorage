package com.blackwell.arraysort;

/**
 * На самом деле, некоторая разновидность блочной сортировки. В один блок будут попадать числа с равными k битами.
 * Асимптотика такая же, как и у LSD версии. Реализация очень похожа на блочную сортировку, но проще.
 * В ней используется функция digit, определенная в реализации LSD версии.
 */

public class MSD {

    private static final int BITS_PER_BYTE =   8;
    private static final int BITS_PER_INT  =  32;   // each Java int is 32 bits
    private static final int R             = 256;   // extended ASCII alphabet size
    private static final int CUTOFF        =  15;   // cutoff to insertion sort

    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();

        int n = arr.length;
        int[] aux = new int[n];
        sort(arr, 0, n-1, 0, aux);

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }

    private static void sort(int[] a, int lo, int hi, int d, int[] aux) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi);
            return;
        }

        // compute frequency counts (need R = 256)
        int[] count = new int[R+1];
        int mask = R - 1;   // 0xFF;
        int shift = BITS_PER_INT - BITS_PER_BYTE*d - BITS_PER_BYTE;
        for (int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            count[c + 1]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            aux[count[c]++] = a[i];
        }

        // copy back
        System.arraycopy(aux, 0, a, lo, hi + 1 - lo);

        // no more bits
        if (d == 4) return;

        // recursively sort for each character
        if (count[0] > 0)
            sort(a, lo, lo + count[0] - 1, d+1, aux);
        for (int r = 0; r < R; r++)
            if (count[r+1] > count[r])
                sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && a[j] < a[j-1]; j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
