package com.blackwell.arraysort;

/**
 * Представим каждое число в двоичном виде. На каждом шаге алгоритма будем сортировать числа таким образом,
 * чтобы они были отсортированы по первым k * i битам, где k – некоторая константа. Из данного определения следует,
 * что на каждом шаге достаточно стабильно сортировать элементы по новым k битам. Для этого идеально подходит
 * сортировка подсчетом (необходимо 2k памяти и времени, что немного при удачном выборе константы).
 * Асимптотика: O(n), если считать, что числа фиксированного размера (а в противном случае нельзя было бы считать,
 * что сравнение двух чисел выполняется за единицу времени). Реализация довольно проста.
 */

public class LSD {
    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();

        final int BITS_PER_BYTE = 8;
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = arr.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int num : arr) {
                int c = (num >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++)
                    count[r] += shift1;
                for (int r = R / 2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int num : arr) {
                int c = (num >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = num;
            }

            // copy back
            System.arraycopy(aux, 0, arr, 0, n);
        }



        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
