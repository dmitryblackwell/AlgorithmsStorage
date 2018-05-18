package com.blackwell.arraysort;
/*
Создадим массив размера r – l, где l – минимальный, а r – максимальный элемент массива.
После этого пройдем по массиву и подсчитаем количество вхождений каждого элемента.
Теперь можно пройти по массиву значений и выписать каждое число столько раз,
сколько нужно. Асимптотика – O(n + r — l). Можно модифицировать этот алгоритм,
чтобы он стал стабильным: для этого определим место, где должно стоять
очередное число (это просто префиксные суммы в массиве значений) и будем
идти по исходному массиву слева направо, ставя элемент на правильное место
и увеличивая позицию на 1. Эта сортировка не тестировалась, поскольку
большинство тестов содержало достаточно большие числа, не позволяющие
создать массив требуемого размера. Однако она, тем не менее, пригодилась.
*/
public class Counting {
    public static long sort(int[] array) {
        long StartTime = System.nanoTime();

        // array to be sorted in, this array is necessary
        // when we sort object datatypes, if we don't,
        // we can sort directly into the input array
        int[] aux = new int[array.length];

        // find the smallest and the largest value
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
            else if (array[i] > max) max = array[i];
        }

        // init array of frequencies
        int[] counts = new int[max - min + 1];

        // init the frequencies
        for (int anArray : array) {
            counts[anArray - min]++;
        }

        // recalculate the array - create the array of occurences
        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i-1];
        }

        // Sort the array right to the left
        // 1) look up in the array of occurences the last occurence of the given value
        // 2) place it into the sorted array
        // 3) decrement the index of the last occurence of the given value
        // 4) continue with the previous value of the input array (goto: 1), terminate if all values were already sorted
        for (int i = array.length - 1; i >= 0; i--) {
            aux[counts[array[i] - min]--] = array[i];
        }

        System.arraycopy(aux,0,array,0,array.length);

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
