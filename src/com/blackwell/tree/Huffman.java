package com.blackwell.tree;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Huffman {

    public static class Node implements Comparable<Node> {

        int value;
        int weight;
        Node leftTree;
        Node rightTree;
        Node parent;


        Node() { parent = null; }

        Node(int v, int w, Node lTree, Node rTree, Node par ) {
            value = v;
            weight = w;
            leftTree = lTree;
            rightTree = rTree;
            parent = par;
        }

        @Override
        public int compareTo(Node rhs) { return weight - rhs.weight; }

        @Override
        public String toString() {
            String str = "";
            str += this.value;
            return str;
        }
    }


    public static class Tree {

        private int size;
        private Node root = new Node();
        private PriorityQueue<Node> queue = new PriorityQueue<>();
        ArrayList<String> path = new ArrayList<>();
        ArrayList<Character> value = new ArrayList<>();

        Tree(int[] freq, char[] code) {
            this.size = freq.length;

            if (freq.length != code.length)
                throw new UnsupportedOperationException("Error: Character and code length mismatch.");

            for (int i = 0; i < this.size; i++)
                queue.offer(new Node(code[i], freq[i], null, null, null));

            create();
            create(this.root, "");
        }

        // setters/getters

        private void create() {
            while (queue.size() > 1) {
                Node tempL = queue.poll();
                Node tempR = queue.poll();

                assert tempL != null;
                assert tempR != null;
                Node parent = new Node(0, tempL.weight+tempR.weight, tempL, tempR, null);

                tempL.parent = parent;
                tempR.parent = parent;

                queue.offer(parent);
                this.size++;
            }

            this.root = queue.peek();
        }

        private void create(Node current, String str) {
            if (current == null) return;

            if (current.leftTree == null && current.rightTree == null) {
                char tempChar;

                if (current.value == 10)
                    tempChar = 'n';
                else
                    tempChar = (char)current.value;

                this.value.add(tempChar);
                this.path.add(str);
            }
            str += "0";
            create(current.leftTree, str);

            str = str.substring(0, str.length()-1);
            str += "1";
            create(current.rightTree, str);
        }

        /**
         * display given huffman tree using pre-order traversal
         * global variable used for representing 'levels' of tree
         * @current -- root of tree to be displayed
         */
        String tacks = "";
        void getTree(Node current) {

            if (current == null) return;

            if (current.leftTree == null
                    && current.rightTree == null) {
                switch (current.value) {
                    case 32: System.out.println(tacks + current.weight + ": sp"); break;
                    case 10: System.out.println(tacks + current.weight + ": nl"); break;
                    default:  System.out.println(tacks + current.weight + ": " + (char)current.value); break;
                }
            }
            else
                System.out.println(tacks + current.weight);

            // increment level marker
            tacks += "- ";
            // recursively call in pre-order
            getTree(current.leftTree);
            getTree(current.rightTree);
            // decrement level marker
            tacks = tacks.substring(0, tacks.length()-2);
        }

        String encode(String input){
            StringBuilder sb = new StringBuilder();

            for (int x = 0; x < input.length(); x++)
                for (int i = 0; i < value.size(); i++)
                    if (value.get(i) == input.charAt(x))
                        sb.append(path.get(i));

            return sb.toString();
        }


        String decode(String bits) {
            StringBuilder sb = new StringBuilder();
            // iterate through bits
            for (int i = 0; i < bits.length(); i++)
                if (!getChar(bits.substring(0, i+1)).equals("")) {
                    sb.append(getChar(bits.substring(0, i+1)));
                    bits = bits.substring(i+1);
                    i = 0;
                }

            return sb.toString();
        }

        private String getChar(String bits) {
            String character = "";

            for (int i = 0; i < path.size(); i++)
                if (path.get(i).equals(bits))
                    character = value.get(i).toString();

            return character;
        }
    }


    public static void main(String[] args) {
        // fields
        int freq[] = {10, 15, 12, 3, 4, 13, 1};
        char code[] = {'a', 'e', 'i', 's', 't', ' ', '\n'};

        // build Huffman Tree using given codes/frequencies
        Tree hTree = new Tree(freq, code);

        // display contents of Huffman Tree in Pre-Order Traversal
        System.out.println("Display Tree:");
        Node curr = hTree.root;
        hTree.getTree(curr);
        System.out.println();

        // encode 'tea'
        System.out.println("Encode 'tea': " + hTree.encode("tea") +"\n");

        // decode 'tea' -- using the actual methods built in
        System.out.println("Decode '" + hTree.encode("tea") + "': " +
                hTree.decode(hTree.encode("tea")));
    }
}