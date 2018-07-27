package com.other.challenges.binarysearchtreechecker;

/***
 * Check if a binary tree is a binary search tree.
 */
public class BinarySearchTreeChecker {

    /***
     * Head node of the binary tree.
     */
    private Node<Integer> head;

    public BinarySearchTreeChecker(Node<Integer> head) {
        this.head = head;
    }

    /**
     * Check if binary tree is binary search tree.
     * @return [boolean] if is BST
     */
    public boolean isBST() {
        return checkIsBSTRecursive(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean checkIsBSTRecursive(Node<Integer> currentNode, Integer minLimit, Integer maxLimit) {
        if (currentNode == null) return true;
        if ((currentNode.getValue() < minLimit || currentNode.getValue() > maxLimit)) return false;

        return checkIsBSTRecursive(currentNode.getLeft(), minLimit, currentNode.getValue()) &&
                checkIsBSTRecursive(currentNode.getRight(), currentNode.getValue(), maxLimit);
    }
}
