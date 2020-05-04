package com.leetcode.breadthfirstsearch;

import com.leetcode.datastructures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 */
public class MinimumDepthofBinaryTree {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int depth = 1;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> levels = new LinkedList<Integer>();
        queue.add(root);
        levels.add(1);
        while(!queue.isEmpty()) {
            Integer level = levels.poll();
            TreeNode current = queue.poll();
            if(current.left == null && current.right == null) {
                depth = level;
                break;
            }
            if(current.left != null) {
                queue.add(current.left);
                levels.add(level + 1);
            }
            if(current.right != null) {
                queue.add(current.right);
                levels.add(level + 1);
            }


        }

        return depth;
    }
}
