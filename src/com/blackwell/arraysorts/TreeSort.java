package com.blackwell.arraysorts;

import java.util.ArrayList;

/**
 * Будем вставлять элементы в двоичное дерево поиска. После того,
 * как все элементы вставлены достаточно обойти дерево в глубину и
 * получить отсортированный массив. Если использовать сбалансированное дерево,
 * например красно-черное, асимптотика будет равна O(nlogn) в худшем, среднем и лучшем случае.
 */

public class TreeSort {
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

    private static ArrayList<Integer> storeSorted(Node n, ArrayList<Integer> arr){
        if(n != null){
            storeSorted(n.left, arr);
            arr.add(n.value);
            storeSorted(n.right, arr);
        }
        return arr;
    }

    public static void main(int[] arr) {
        long StartTime = System.nanoTime();
        Node root = insert(null, arr[0]);

        for (int anArr : arr)
            insert(root, anArr);

        ArrayList<Integer> list = storeSorted(root, new ArrayList<>());
        //System.out.println(list);

        long EndTime = System.nanoTime();
        System.out.println("TreeSort for " + arr.length + " elements: "+(EndTime-StartTime) + " : "+ (EndTime-StartTime)*Math.pow(10,-9)+" secs");

    }
}
