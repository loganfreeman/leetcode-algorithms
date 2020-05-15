package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {


    public List<List<Integer>> permuteBackTrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, arrayToList(nums), new ArrayList<Integer>());
        return result;
    }

    public List<Integer> arrayToList(int[] nums) {
        List<Integer> intList = new ArrayList<Integer>(nums.length);
        for (int i : nums)
        {
            intList.add(i);
        }
        return intList;
    }

    public void backtrack(List<List<Integer>> results, List<Integer> arr, List<Integer> current) {
        if(arr.isEmpty()) {
            results.add(current);
        } else {
            for(int i = 0 ; i < arr.size(); i++) {
                List<Integer> newArr = new ArrayList<Integer>(arr);
                newArr.remove(i);
                List<Integer> newCurrent = new ArrayList<Integer>(current);
                newCurrent.add(arr.get(i));
                backtrack(results, newArr, newCurrent);
            }
        }
    }

    /**
     * We can also recursively solve this problem. Swap each element with each element after it.
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result){
        if(start==nums.length-1){
            ArrayList<Integer> list = new ArrayList<>();
            for(int num: nums){
                list.add(num);
            }
            result.add(list);
            return;
        }

        for(int i=start; i<nums.length; i++){
            swap(nums, i, start);
            helper(start+1, nums, result);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Loop through the array, in each iteration, a new number is added to different locations of results of previous iteration. Start from an empty List.
     * @param num
     * @return
     *
     * [1]
     * [2, 1]
     * [1, 2]
     * [3, 2, 1]
     * [2, 3, 1]
     * [2, 1, 3]
     * [3, 1, 2]
     * [1, 3, 2]
     * [1, 2, 3]
     */
    public ArrayList<ArrayList<Integer>> permuteIterative(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }
}
