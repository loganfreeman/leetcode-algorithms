package com.leetcode.tree;

import com.leetcode.datastructures.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinDepth {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<Integer> counts = new LinkedList<Integer>();

        nodes.add(root);
        counts.add(1);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();

            if(curr.left == null && curr.right == null){
                return count;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }

        return 0;
    }
}
