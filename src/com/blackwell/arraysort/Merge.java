package com.blackwell.arraysort;

/*
Сортировка, основанная на парадигме «разделяй и властвуй». Разделим массив пополам, рекурсивно отсортируем части,
после чего выполним процедуру слияния: поддерживаем два указателя, один на текущий элемент первой части, второй – на
 текущий элемент второй части. Из этих двух элементов выбираем минимальный, вставляем в ответ и сдвигаем указатель,
 соответствующий минимуму. Слияние работает за O(n), уровней всего logn, поэтому асимптотика O(nlogn). Эффективно
 заранее создать временный массив и передать его в качестве аргумента функции. Эта сортировка рекурсивна,
  как и быстрая, а потому возможен переход на квадратичную при небольшом числе элементов.
 */

public class Merge {

    public static long sort(int[] arr){
        long start = System.nanoTime();

        mergeSort(arr, new int[arr.length],0,arr.length-1);

        return System.nanoTime() - start;
    }
    /**
     * Merge sort (descending order)
     * @param array array to be sorted
     * @param aux auxiliary array of the same size as array
     * @param left first index, which can be touched
     * @param right last index, which can be touched
     */
    private static void mergeSort(int[] array, int[] aux, int left, int right) {
        if (left == right) return;
        int middleIndex = (left + right)/2;
        mergeSort(array, aux, left, middleIndex);
        mergeSort(array, aux, middleIndex + 1, right);
        merge(array, aux, left, right);
        for (int i = left; i <= right; i++) {
            array[i] = aux[i];
        }
    }
    /**
     * Merge procedure for merge sort
     * @param array array to be merged
     * @param aux auxiliary array of the same size as array
     * @param left first index, which can be touched
     * @param right last index, which can be touched
     */
    private static void merge(int[] array, int[] aux, int left, int right) {
        int middleIndex = (left + right)/2;
        int leftIndex = left;
        int rightIndex = middleIndex + 1;
        int auxIndex = left;
        while (leftIndex <= middleIndex && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                aux[auxIndex] = array[leftIndex++];
            } else {
                aux[auxIndex] = array[rightIndex++];
            }
            auxIndex++;
        }
        while (leftIndex <= middleIndex) {
            aux[auxIndex] = array[leftIndex++];
            auxIndex++;
        }
        while (rightIndex <= right) {
            aux[auxIndex] = array[rightIndex++];
            auxIndex++;
        }
    }
}
