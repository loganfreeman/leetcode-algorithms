package com.leetcode.dynamicprogramming;
// https://www.programcreek.com/2015/03/leetcode-house-robber-iii-java/


import com.leetcode.datastructures.TreeNode;

/**
 * The houses form a binary tree. If the root is robbed, its left and right can not be robbed.
 *
 * Traverse down the tree recursively. We can use an array to keep 2 values: the maximum money when a root is selected and the maximum value when a root if NOT selected.
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        if(root == null)
            return 0;

        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode root){
        if(root == null){
            int[] result = {0, 0};
            return result;
        }

        int[] result = new int[2];
        int[] left = helper(root.left);
        int[] right = helper (root.right);

        // result[0] is when root is selected, result[1] is when not.
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }
}
