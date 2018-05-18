package com.blackwell.tree;

import java.util.Random;

public class Treap<T extends Comparable> {
    private static final Random rand = new Random();
    private Node<T> root;

    public static void main(String[] args) {
        Treap<Integer> t =new Treap<>();
        Random R = new Random();
        for(int i=0; i<10; ++i)
            t.add(R.nextInt(100)); //Integer.MAX_VALUE)


        System.out.println(t.depth());
        System.out.println(t.elements());
        System.out.println(t);
    }

    public float average() { return getSum(root) / maxDepth(root);}
    private int getSum(Node<T> n){
        if (n == null) return 0;
        return (int) n.data + getSum(n.left) + getSum(n.right);
    }


    public int depth() { return maxDepth(root);}
    private int maxDepth(Node node) {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public int elements() { return getElementCount(root); }
    private int getElementCount(Node<T> n){
        if (n == null) return 0;
        return 1 + getElementCount(n.left) + getElementCount(n.right);
    }


    public void add(T data) {
        root = add(root, data);
    }
    private Node<T> add(Node<T> node, T data) {
        if (node == null)
            return new Node<T>(data);

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left = add(node.left, data);
            if (node.priority > node.left.priority)
                return rotateRight(node);
        } else if (compare > 0) {
            node.right = add(node.right, data);
            if (node.priority > node.right.priority)
                return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> lnode = node.left;
        node.left = lnode.right;
        lnode.right = node;
        return lnode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rnode = node.right;
        node.right = rnode.left;
        rnode.left = node;
        return rnode;
    }

    public void remove(T data) {
        root = remove(root, data);
    }

    private Node<T> remove(Node<T> node, T data) {
        if (node != null) {
            int compare = data.compareTo(node.data);
            if (compare < 0) {
                node.left = remove(node.left, data);
            } else if (compare > 0) {
                node.right = remove(node.right, data);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    node.data = first(node.right);
                    node.right = remove(node.right, node.data);
                }
            }
        }
        return node;
    }


    public boolean contains(T data) {
        Node<T> node = root;
        while (node != null) {
            int compare = data.compareTo(node.data);
            if (compare < 0) node = node.left;
            else if (compare > 0) node = node.right;
            else return true;
        }
        return false;
    }

    public T first() {
        return first(root);
    }

    private T first(Node<T> searchNode) {
        Node<T> node = searchNode;
        while (node.left != null) node = node.left;
        return node.data;
    }

    @Override
    public String toString() {
        return "Treap{" +
                "root=" + root +
                '}';
    }


    private static class Node<T extends Comparable> {
        Node<T> right, left;
        final int priority = rand.nextInt();
        T data;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + data +
                    ", priority=" + priority +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
