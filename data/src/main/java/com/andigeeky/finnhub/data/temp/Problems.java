package com.andigeeky.finnhub.data.temp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Problems {

    /**
     * Find square of a number without using *, / and pow()
     * Input: 5
     * Output: 25
     */
    public static int findSquareOfNumber(int number) {
        int square = number;
        for (int i = 1; i < number; i++) {
            square = square + number;
        }
        return square;
    }

    /**
     * Find square root of a number without using *, / and pow()
     * Input: 25
     * Output: 5
     */
    public static int findSquareRootOfNumber(int number) {
        int start = 1;
        int end = number;
        int boundaryIndex = -1;
        while (start < end) {
            int center = start + (end - start) / 2;
            if (number / center >= center) {
                boundaryIndex = center;
                start = center + 1;
            } else {
                end = center - 1;
            }
        }
        return boundaryIndex;
    }

    /**
     * A sorted array of unique integers was rotated at an unknown pivot.
     * For example, [10, 20, 30, 40, 50] becomes [30, 40, 50, 10, 20]. Find the index of the minimum element in this array.
     * All the numbers are unique.
     * <p>
     * Input: [30, 40, 50, 10, 20]
     * Output: 3
     */
    public static int findMinInRotatedArrayV1(List<Integer> elements) {
        long result = 0;
        for (int index = 0; index < elements.size() - 1; index++) {
            if (elements.get(index) > elements.get(index + 1)) return index + 1;
        }
        return (int) result;
    }

    public static int findMinInRotatedArrayV2(List<Integer> elements) {
        int result = 0;
        int start = 0;
        int end = elements.size() - 1;
        while (start <= end) {
            int center = start + (end - start) / 2;
            if (center == 0) return 0;
            if (elements.get(center) <= elements.get(center - 1)) {
                result = center;
                end = center - 1;
            } else {
                start = center + 1;
            }
        }
        return result;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int start = -1;
        int end = -1;
        if (nums1.length <= nums2.length) {
            for (int k : nums1) {
                for (int j = 0; j < nums2.length; j++) {
                    if (k == nums2[j]) {
                        if (start == -1) start = j;
                        else end = j;
                    }
                }
            }
        } else {
            for (int k : nums2) {
                for (int j = 0; j < nums1.length; j++) {
                    if (nums1[j] == k) {
                        if (start == -1) start = j;
                        else end = j;
                    }
                }
            }
        }
        return Arrays.copyOfRange(nums2, start, end - 1);
    }

    /**
     * Max depth of a binary tree is the longest root-to-leaf path.
     * Given a binary tree, find its max depth.
     */
    public static int treeMaxDepth(Node<Integer> root) {
        if (root == null) return 0;
        int leftDepth = 1 + treeMaxDepth(root.left);
        int rightDepth = 1 + treeMaxDepth(root.right);
        System.out.println("leftDepth: " + leftDepth + " rightDepth:" + rightDepth);
        return findMax(leftDepth, rightDepth);
    }

    public static int findMax(int leftDepth, int rightDepth) {
        return Math.max(leftDepth, rightDepth);
    }

    public static int countVisibleNodes(Node<Integer> root, int max) {
        if (root == null) return 0;
        int total = 0;
        System.out.println("root: " + root.val + " max:" + max + " count: " + total);
        if (root.val >= max) {
            total++;
        }
        total += countVisibleNodes(root.left, Math.max(max, root.val));
        total += countVisibleNodes(root.right, Math.max(max, root.val));
        return total;
    }

    public static int isBalanced(Node<Integer> root) {
        if (root == null) return 0;
        int left = isBalanced(root.left);
        int right = isBalanced(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1 ) {
            return -1;
        }
        System.out.println("root: " + root.val + " left:" + left + " right: " + right + " diff: " + (left-right));
        return Math.max(left, right) + 1;
    }

    @SuppressWarnings("NewApi")
    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
