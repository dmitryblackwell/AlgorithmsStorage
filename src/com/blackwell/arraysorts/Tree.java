package com.blackwell.arraysorts;

import java.util.ArrayList;

/**
 * Будем вставлять элементы в двоичное дерево поиска. После того,
 * как все элементы вставлены достаточно обойти дерево в глубину и
 * получить отсортированный массив. Если использовать сбалансированное дерево,
 * например красно-черное, асимптотика будет равна O(nlogn) в худшем, среднем и лучшем случае.
 */

public class Tree {
    private static class Node{
        int value;
        Node left,right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    private static Node insert(Node n, int v){
        if (n == null)
            return new Node(v);

        if (v < n.value)
            n.left = insert(n.left,v);
        else if (v >= n.value)
            n.right = insert(n.right,v);

        return n;
    }

    private static void storeSorted(Node n, ArrayList<Integer> arr){
        if(n != null){
            storeSorted(n.left, arr);
            arr.add(n.value);
            storeSorted(n.right, arr);
        }
    }

    public static long sort(int[] arr) {
        long StartTime = System.nanoTime();
        Node root = insert(null, arr[0]);

        for (int anArr : arr)
            insert(root, anArr);

        ArrayList<Integer> list = new ArrayList<>();
        storeSorted(root, list);
        for (int i=0; i<arr.length; ++i)
            arr[i] = list.get(i);

        long EndTime = System.nanoTime();
        return EndTime-StartTime;
    }
}
