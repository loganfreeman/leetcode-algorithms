package com.leetcode.breadthfirstsearch;
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

import com.leetcode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new  ArrayList();

        if(root == null){
            return result;
        }

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.offer(root);

        ArrayList<Integer> numberList = new ArrayList<Integer>();

        // need to track when each level starts
        while(!current.isEmpty()){
            TreeNode head = current.poll();

            numberList.add(head.val);

            if(head.left != null){
                next.offer(head.left);
            }
            if(head.right!= null){
                next.offer(head.right);
            }

            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                result.add(numberList);
                numberList = new ArrayList<Integer>();
            }
        }

        //return Collections.reverse(result);
        List<List<Integer>> reversedResult = new ArrayList();
        for(int i=result.size()-1; i>=0; i--){
            reversedResult.add(result.get(i));
        }

        return reversedResult;
    }
}
