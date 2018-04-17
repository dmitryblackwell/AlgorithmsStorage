package com.blackwell.arraysorts;

/**
 * Еще одна модификация сортировки пузырьком. Для того, чтобы избавиться от «черепах»,
 * будем переставлять элементы, стоящие на расстоянии. Зафиксируем его и будем идти слева
 * направо, сравнивая элементы, стоящие на этом расстоянии, переставляя их, если необходимо.
 * Очевидно, это позволит «черепахам» быстро добраться в начало массива. Оптимально изначально
 * взять расстояние равным длине массива, а далее делить его на некоторый коэффициент, равный
 * примерно 1.247. Когда расстояние станет равно единице, выполняется сортировка пузырьком.
 * В лучшем случае асимптотика равна O(nlogn), в худшем – O(n2). Какая асимптотика в среднем
 * мне не очень понятно, на практике похоже на O(nlogn).
 */
public class Comb{
    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();

        int n = arr.length;

        // initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped) {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    // Swap arr[i] and arr[i+gap]
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;

                    // Set swapped
                    swapped = true;
                }
            }
        }

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }

    // To find gap between elements
    static private int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        if (gap < 1)
            return 1;
        return gap;
    }


}
