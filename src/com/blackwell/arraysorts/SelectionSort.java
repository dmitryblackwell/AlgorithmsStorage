package com.blackwell.arraysorts;

/**
 * На очередной итерации будем находить минимум в массиве после текущего
 * элемента и менять его с ним, если надо. Таким образом, после i-ой итерации
 * первые i элементов будут стоять на своих местах. Асимптотика: O(n2) в лучшем,
 * среднем и худшем случае. Нужно отметить, что эту сортировку можно реализовать
 * двумя способами – сохраняя минимум и его индекс или просто переставляя текущий
 * элемент с рассматриваемым, если они стоят в неправильном порядке. Первый способ
 * оказался немного быстрее, поэтому он и реализован.
 */
public class SelectionSort {
    public static int[] main(String[] args) {
        long StartTime = System.nanoTime();

        int[] array = Utils.toIntArray(args);
        for (int i = 0; i < array.length - 1; i++) {

            int maxIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > array[maxIndex])
                    maxIndex = j;
            }

            int tmp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = tmp;
        }

        long EndTime = System.nanoTime();
        System.out.println("SelectionSort for " + array.length + "elements: "+(EndTime-StartTime));
        return array;
    }
}
