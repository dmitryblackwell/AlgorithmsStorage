package com.blackwell.arraysorts;

public class Heap {
    public static long sort(int[] arr) {
        long start = System.nanoTime();

        int n = arr.length;

        // Build heap (rearrange array)
        for(int i= n/2 - 1; i>=0; --i)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; --i){
            // Move current root to end
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }

        return System.nanoTime()-start;
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private static void heapify(int[] arr, int n, int i){
        int largest = i; // Initialize largest as root
        int left = 2*i + 1;
        int right = 2*i + 2;

        // if left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // if right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // if largest is not root
        if (largest != i){
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            //Recursively heapify the affected sub-tree
            heapify(arr,n,largest);
        }
    }
}
