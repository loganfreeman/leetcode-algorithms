package com.leetcode.breadthfirstsearch;

import com.leetcode.datastructures.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * Output: [1, 3, 9]
 */
public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> result = new LinkedList<>();
        if(root == null) return result;
        while(!queue.isEmpty()) {
            int n = queue.size();
            int v = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                v = Math.max(v, node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            result.add(v);
        }
        return result;
    }
}
