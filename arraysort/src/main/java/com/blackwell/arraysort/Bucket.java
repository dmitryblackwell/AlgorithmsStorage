package com.blackwell.arraysort;

import java.util.ArrayList;
import java.util.Collections;

/*
(также известна как корзинная и карманная сортировка). Пусть l – минимальный, а r – максимальный элемент массива. Разобьем элементы на блоки, в первом будут элементы от l до l + k, во втором – от l + k до l + 2k и т.д., где k = (r – l) / количество блоков. В общем-то, если количество блоков равно двум, то данный алгоритм превращается в разновидность быстрой сортировки. Асимптотика этого алгоритма неясна, время работы зависит и от входных данных, и от количества блоков. Утверждается, что на удачных данных время работы линейно. Реализация этого алгоритма оказалась одной из самых трудных задач. Можно сделать это так: просто создавать новые массивы, рекурсивно их сортировать и склеивать. Однако такой подход все же довольно медленный и меня не устроил. В эффективной реализации используется несколько идей:

1) Не будем создавать новых массивов. Для этого воспользуемся техникой сортировки подсчетом – подсчитаем количество элементов в каждом блоке, префиксные суммы и, таким образом, позицию каждого элемента в массиве.

2) Не будем запускаться из пустых блоков. Занесем индексы непустых блоков в отдельный массив и запустимся только от них. 

3) Проверим, отсортирован ли массив. Это не ухудшит время работы, так как все равно нужно сделать проход с целью нахождения минимума и максимума, однако позволит алгоритму ускориться на частично отсортированных данных, ведь элементы вставляются в новые блоки в том же порядке, что и в исходном массиве.

4) Поскольку алгоритм получился довольно громоздким, при небольшом количестве элементов он крайне неэффективен. До такой степени, что переход на сортировку вставками ускоряет работу примерно в 10 раз.

Осталось только понять, какое количество блоков нужно выбрать. На рандомизированных тестах мне удалось получить следующую оценку: 1500 блоков для 107 элементов и 3000 для 108. Подобрать формулу не удалось – время работы ухудшалось в несколько раз.
*/

public class Bucket {
    public static long sort(int[] array) {
        long start = System.nanoTime();
        int bucketCount = array.length;

        int high = array[0];
        int low = array[0];
        for (int i = 1; i < array.length; i++) { //find the range of input elements
            if (array[i] > high) high = array[i];
            if (array[i] < low) low = array[i];
        }
        double interval = ((double)(high - low + 1))/bucketCount; //range of one bucket

        ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) { //initialize buckets
            buckets[i] = new ArrayList();
        }

        for (int i = 0; i < array.length; i++) { //partition the input array
            buckets[(int)((array[i] - low)/interval)].add(array[i]);
        }

        int pointer = 0;
        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]); //mergeSort
            for (int j = 0; j < buckets[i].size(); j++) { //merge the buckets
                array[pointer] = buckets[i].get(j);
                pointer++;
            }
        }

        return System.nanoTime() - start;
    }
}
