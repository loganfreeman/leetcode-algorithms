package com.leetcode.depthfirstsearch;

import com.leetcode.datastructures.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */
public class MaximumProductofSplittedBinaryTree {
    int res = 0;
    int M = 1000000007;
    public int maxProduct(TreeNode root) {
        int total = sum(root);
        sum(root, total);
        return res % M;
    }

    public int sum(TreeNode root) {
        if(root == null) return 0;
        int left = sum(root.left);
        int right = sum(root.right);
        return left + right + root.val;
    }
    public int sum(TreeNode root, int total) {

        if(root == null) return 0;
        int left = sum(root.left, total);
        int right = sum(root.right, total);
        int s = left + right + root.val;

        res = Math.max(res, s * (total - s));
        return s;
    }
}
