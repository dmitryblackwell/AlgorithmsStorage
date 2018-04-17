package com.blackwell.arraysorts;

/**
 * Будем идти по массиву слева направо. Если текущий элемент больше следующего,
 * меняем их местами. Делаем так, пока массив не будет отсортирован. Заметим, что
 * после первой итерации самый большой элемент будет находиться в конце массива, на
 * правильном месте. После двух итераций на правильном месте будут стоять два
 * наибольших элемента, и так далее. Очевидно, не более чем после n итераций
 * массив будет отсортирован. Таким образом, асимптотика в худшем и среднем
 * случае – O(n2), в лучшем случае – O(n).
 */
public class Bubble { // снипицы
    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();

        int tmp = 0;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 1; j < arr.length - i; ++j) {
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
