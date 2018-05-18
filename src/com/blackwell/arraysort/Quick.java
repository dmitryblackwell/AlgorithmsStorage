package com.blackwell.arraysort;

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
public class Quick{

    private static int[] array;

    public static long sort(int[] arr) {
        int startIndex = 0;
        int endIndex = arr.length-1;
        array=new int[arr.length];
        System.arraycopy(arr,0,array,0,arr.length);

        long StartTime = System.nanoTime();

        doSort(startIndex, endIndex);

        long EndTime = System.nanoTime();
        System.arraycopy(array,0,arr,0,arr.length);
        return EndTime-StartTime;
    }

    private static void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }

}
