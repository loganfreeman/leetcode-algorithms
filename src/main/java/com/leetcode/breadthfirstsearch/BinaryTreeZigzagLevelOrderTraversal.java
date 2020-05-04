package com.leetcode.breadthfirstsearch;
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

import com.leetcode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root==null){
            return result;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();

        nodeQueue.offer(root);
        levelQueue.offer(1);//start from 1

        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();

            List<Integer> l=null;
            if(result.size()<level){
                l = new LinkedList<>();
                result.add(l);
            }else{
                l = result.get(level-1);
            }
            LinkedList ll = (LinkedList) l;
            if(level % 2 == 0) {
                ll.addFirst(node.val);
            } else {
                ll.add(node.val);
            }

            if(node.left!=null){
                nodeQueue.offer(node.left);
                levelQueue.offer(level+1);
            }

            if(node.right!=null){
                nodeQueue.offer(node.right);
                levelQueue.offer(level+1);
            }
        }

        return result;
    }
}
