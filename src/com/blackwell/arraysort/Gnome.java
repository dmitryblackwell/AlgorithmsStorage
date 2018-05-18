package com.blackwell.arraysort;

/**
 * Алгоритм похож на сортировку вставками. Поддерживаем указатель на текущий элемент,
 * если он больше предыдущего или он первый — смещаем указатель на позицию вправо,
 * иначе меняем текущий и предыдущий элементы местами и смещаемся влево.
 */
public class Gnome {
    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();

        int i=1;
        while (i<arr.length){
            if (i == 0 || arr[i-1] <= arr[i])
                i++;
            else {
                int tmp = arr[i];
                arr[i] = arr[i-1];
                arr[i-1] = tmp;
                i--;
            }
        }

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
