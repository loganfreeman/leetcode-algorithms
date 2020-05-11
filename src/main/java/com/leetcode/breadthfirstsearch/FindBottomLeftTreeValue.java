package com.leetcode.breadthfirstsearch;

import com.leetcode.datastructures.TreeNode;

import java.util.LinkedList;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Example 1:
 * Input:
 *
 *     2
 *    / \
 *   1   3
 *
 * Output:
 * 1
 * Example 2:
 * Input:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * Output:
 * 7
 */
public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i = 0; i < n; i++) {
                TreeNode treeNode = queue.poll();
                if(i == 0) res = treeNode.val;
                if(treeNode.left != null) queue.offer(treeNode.left);
                if(treeNode.right != null) queue.offer(treeNode.right);
            }

        }
        return res;
    }

    public int findBottomLeftValue2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        TreeNode treeNode = null;
        while(!queue.isEmpty()) {
            treeNode = queue.poll();
            if(treeNode.right != null) queue.offer(treeNode.right);

            if(treeNode.left != null) queue.offer(treeNode.left);

        }
        return treeNode.val;
    }
}
