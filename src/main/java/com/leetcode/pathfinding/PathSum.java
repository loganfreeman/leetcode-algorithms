package com.leetcode.pathfinding;

import com.leetcode.datastructures.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 */
public class PathSum {
    /**
     * Add all node to a queue and store sum value of each node to another queue. When it is a leaf node, check the stored sum value.
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumWithQueue(TreeNode root, int sum) {
        if(root == null) return false;

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> values = new LinkedList<Integer>();

        nodes.add(root);
        values.add(root.val);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();

            if(curr.left == null && curr.right == null && sumValue==sum){
                return true;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue+curr.left.val);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue+curr.right.val);
            }
        }

        return false;
    }

    // depth first search
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.val == sum && (root.left == null && root.right == null))
            return true;

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}
