package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * <p>
 * If there are multiple solutions, return any subset is fine.
 * <p>
 * Example:
 * <p>
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    List<Integer> answer;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();

        Arrays.sort(nums);

        int[] max = new int[1];
        List<Integer> result = new ArrayList<Integer>();
        helper(nums, 0, result, max);
        return answer;
    }

    public void helper(int[] nums, int start, List<Integer> result, int[] max) {
        if (result.size() > max[0]) {
            max[0] = result.size();
            answer = new ArrayList<Integer>(result);
        }

        if (start == nums.length)
            return;

        for (int i = start; i < nums.length; i++) {
            if (result.size() == 0) {
                result.add(nums[i]);
                helper(nums, i + 1, result, max);
                result.remove(result.size() - 1);

            } else {

                int top = result.get(result.size() - 1);
                if (nums[i] % top == 0) {
                    result.add(nums[i]);
                    helper(nums, i + 1, result, max);
                    result.remove(result.size() - 1);
                }
            }
        }
    }


    public List<Integer> largestDivisibleSubsetDP(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0)
            return result;

        Arrays.sort(nums);

        int[] t = new int[nums.length];
        int[] index = new int[nums.length];
        Arrays.fill(t, 1);
        Arrays.fill(index, -1);

        int max = 0;
        int maxIndex = -1;

        for (int i = 0; i < t.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && t[j] + 1 > t[i]) {
                    t[i] = t[j] + 1;
                    index[i] = j;
                }
            }

            if (max < t[i]) {
                max = t[i];
                maxIndex = i;
            }
        }

        int i = maxIndex;
        while (i >= 0) {
            result.add(nums[i]);
            i = index[i];
        }

        return result;
    }
}
