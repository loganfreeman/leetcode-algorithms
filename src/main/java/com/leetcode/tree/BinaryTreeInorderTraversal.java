package com.leetcode.tree;

import com.leetcode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 */
public class BinaryTreeInorderTraversal {

    public void inorderTraversal(TreeNode root, List<Integer> results) {
        if(root == null) return;
        inorderTraversal(root.left, results);
        results.add(root.val);
        inorderTraversal(root.right, results);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        inorderTraversal(root, results);
        return results;
    }
}
