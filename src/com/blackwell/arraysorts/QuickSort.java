package com.blackwell.arraysorts;

/**
 * Выберем некоторый опорный элемент. После этого перекинем все элементы, меньшие его,
 * налево, а большие – направо. Рекурсивно вызовемся от каждой из частей. В итоге получим
 * отсортированный массив, так как каждый элемент меньше опорного стоял раньше каждого
 * большего опорного. Асимптотика: O(nlogn) в среднем и лучшем случае, O(n2). Наихудшая
 * оценка достигается при неудачном выборе опорного элемента. Моя реализация этого алгоритма
 * \совершенно стандартна, идем одновременно слева и справа, находим пару элементов, таких,
 * что левый элемент больше опорного, а правый меньше, и меняем их местами. Помимо чистой
 * быстрой сортировки, участвовала в сравнении и сортировка, переходящая при малом количестве
 * элементов на сортировку вставками. Константа подобрана тестированием, а сортировка вставками
 * — наилучшая сортировка, подходящая для этой задачи (хотя не стоит из-за этого думать, что
 * она самая быстрая из квадратичных).
 */
public class QuickSort{
    public static int[] main(String[] args) {
        long StartTime = System.nanoTime();

        int[] arr = Utils.toIntArray(args);
        quicksort(arr,0,arr.length-1);

        long EndTime = System.nanoTime();
        System.out.println("QuickSort for " + arr.length + "elements: "+(EndTime-StartTime));
        return arr;
    }
    /**
     * Quicksort - pivot is the first element, descending order
     * @param array array to be sorted
     * @param left index of the first element which we can touch
     * @param right index of the first element which we can't touch
     */
    private static void quicksort(int[] array, int left, int right) {
        if (left < right) {
            int boundary = left;
            for (int i = left + 1; i < right; i++) {
                if (array[i] > array[left]) {
                    swap(array, i, ++boundary);
                }
            }
            swap(array, left, boundary);
            quicksort(array, left, boundary);
            quicksort(array, boundary + 1, right);
        }
    }
    /**
     * Swaps the elements of the array
     * @param array array
     * @param left index of the first element
     * @param right index of the second element
     */
    private static void swap(int[] array, int left, int right) {
        int tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
    }
}
