package com.other.challenges.binarysearchtreechecker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearchTreeCheckerTest {

    @Test
    public void checkTreeWithOneNode() {
        Node<Integer> head = new Node<>(5);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertTrue("Tree with one element should be binary search tree.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithGoodThreeNodes() {
        Node<Integer> left = new Node<>(3);
        Node<Integer> right = new Node<>(7);
        Node<Integer> head = new Node<>(5, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertTrue("Three with three good elements should be binary search tree.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithBadThreeNodes() {
        Node<Integer> left = new Node<>(6);
        Node<Integer> right = new Node<>(4);
        Node<Integer> head = new Node<>(5, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertFalse("Three with three bad elements should be binary search tree.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithGoodMultipleNodes() {
//             4
//           /   \
//          2     6
//         / \   / \
//        1   3 5   7
//       /
//      0
        Node<Integer> left = new Node<>(2, new Node<>(1, new Node<>(0), null), new Node<>(3));
        Node<Integer> right = new Node<>(6, new Node<>(5), new Node<>(7));
        Node<Integer> head = new Node<>(4, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertTrue("Three with multiple good elements should be binary search tree.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithGoodMultipleNodes2() {
//              0
//            /   \
//         -4       6
//         / \     / \
//       -7  -2   5   7
//       /   / \       \
//     -9  -3  -1       8
        Node<Integer> left = new Node<>(-4,
                new Node<>(-7, new Node<>(-9), null),
                new Node<>(-2, new Node<>(-3), new Node<>(-1 )));
        Node<Integer> right = new Node<>(6,
                new Node<>(5),
                new Node<>(7, null, new Node<>(8)));
        Node<Integer> head = new Node<>(0, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertTrue("Three with multiple good elements should be binary search tree 2.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithBadMultipleNodes() {
//             4
//           /   \
//          2     6
//         / \   / \
//        1   8 5   7
//       /
//      0
        Node<Integer> left = new Node<>(2, new Node<>(1, new Node<>(0), null), new Node<>(8));
        Node<Integer> right = new Node<>(6, new Node<>(5), new Node<>(7));
        Node<Integer> head = new Node<>(4, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertFalse("Three with multiple bad elements should be binary search tree.", binarySearchTreeChecker.isBST());
    }

    @Test
    public void checkThreeWithBadMultipleNodes2() {
//             4
//           /   \
//          1     8
//         / \   / \
//        0   2 7   9
//       /     /
//     -1     3
        Node<Integer> left = new Node<>(1, new Node<>(0, new Node<>(-1), null), new Node<>(2));
        Node<Integer> right = new Node<>(8, new Node<>(7, new Node<>(3), null), new Node<>(9));
        Node<Integer> head = new Node<>(4, left, right);

        BinarySearchTreeChecker binarySearchTreeChecker = new BinarySearchTreeChecker(head);

        assertFalse("Three with multiple bad elements should be binary search tree 2.", binarySearchTreeChecker.isBST());
    }
}