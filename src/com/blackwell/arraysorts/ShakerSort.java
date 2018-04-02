package com.blackwell.arraysorts;

/**
 * (также известна как сортировка перемешиванием и коктейльная сортировка).
 * Заметим, что сортировка пузырьком работает медленно на тестах, в которых
 * маленькие элементы стоят в конце (их еще называют «черепахами»). Такой
 * элемент на каждом шаге алгоритма будет сдвигаться всего на одну позицию
 * влево. Поэтому будем идти не только слева направо, но и справа налево.
 * Будем поддерживать два указателя begin и end, обозначающих, какой отрезок
 * массива еще не отсортирован. На очередной итерации при достижении end
 * вычитаем из него единицу и движемся справа налево, аналогично, при
 * достижении begin прибавляем единицу и двигаемся слева направо.
 * Асимптотика у алгоритма такая же, как и у сортировки пузырьком,
 * однако реальное время работы лучше.
 */


public class ShakerSort {
    public static void main(int[] array) {
        long StartTime = System.nanoTime();

        for (int i = 0; i < array.length/2; i++) {
            boolean swapped = false;

            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    swapped = true;
                }
            }

            for (int j = array.length - 2 - i; j > i; j--) {
                if (array[j] > array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        long EndTime = System.nanoTime();
        System.out.println("SnakeSort for " + array.length + " elements: "+(EndTime-StartTime) + " : "+ (EndTime-StartTime)*Math.pow(10,-9)+" secs");
    }
}
