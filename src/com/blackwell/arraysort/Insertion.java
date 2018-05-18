package com.blackwell.arraysort;

/**
 * На очередной итерации будем находить минимум в массиве после текущего элемента и менять его с ним,
 * если надо. Таким образом, после i-ой итерации первые i элементов будут стоять на своих местах.
 * Асимптотика: O(n2) в лучшем, среднем и худшем случае. Нужно отметить, что эту сортировку можно
 * реализовать двумя способами – сохраняя минимум и его индекс или просто переставляя текущий элемент
 * с рассматриваемым, если они стоят в неправильном порядке.
 */
public class Insertion {
    public static long sort(int[] array) {
        long StartTime = System.nanoTime();

        for (int i = 0; i < array.length - 1; i++) {
            int j = i + 1;
            int tmp = array[j];

            while (j > 0 && tmp < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }

            array[j] = tmp;
        }

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
