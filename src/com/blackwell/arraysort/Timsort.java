package com.blackwell.arraysort;

/**
 * Гибридная сортировка, совмещающая сортировку вставками и сортировку слиянием. Разобьем элементы массива на несколько
 * подмассивов небольшого размера, при этом будем расширять подмассив, пока элементы в нем отсортированы. Отсортируем
 * подмассивы сортировкой вставками, пользуясь тем, что она эффективно работает на отсортированных массивах.
 * Далее будем сливать подмассивы как в сортировке слиянием, беря их примерно равного размера
 * (иначе время работы приблизится к квадратичному). Для этого удобного хранить подмассивы в стеке,
 * поддерживая инвариант — чем дальше от вершины, тем больше размер, и сливать подмассивы на верхушке только тогда,
 * когда размер третьего по отдаленности от вершины подмассива больше или равен сумме их размеров.
 * Асимптотика: O(n) в лучшем случае и O(nlogn) в среднем и худшем случае. Реализация нетривиальна,
 * твердой уверенности в ней у меня нет, однако время работы она показала довольно неплохое и согласующееся
 * с моими представлениями о том, как должна работать эта сортировка.
 */
public class Timsort {
    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();



        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }

}
