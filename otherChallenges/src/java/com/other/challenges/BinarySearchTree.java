package com.other.challenges;

import java.util.Objects;

public class BinarySearchTree {

    /**
     * Checks if a binary tree contains a node
     * @param root root node of binary tree
     * @param value value to search for
     * @return [boolean]
     */
    public static boolean contains(Node root, int value) {
        if (Objects.isNull(root)) return false;

        if (root.value == value) return true;
        else if (value < root.value) return contains(root.left, value);
             else return contains(root.right, value);
    }

    /**
     * Creates binary tree and checks node existence.
     * @param args
     */
    public static void main(String[] args) {
        Node n11 = new Node(0, null, null);
        Node n12 = new Node(2, null, null);
        Node n21 = new Node(1, n11, n12);
        Node n22 = new Node(3, n21, null);
        Node n3 = new Node(5, null, null);
        Node n2 = new Node(4, n22, n3);

        System.out.println(contains(n2, 0));
        System.out.println(contains(n2, 1));
        System.out.println(contains(n2, 2));
        System.out.println(contains(n2, 3));
        System.out.println(contains(n2, 4));
        System.out.println(contains(n2, 5));
        System.out.println(contains(n2, -1));
        System.out.println(contains(n2, 6));
    }


    private static class Node {
        public int value;
        public Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}


