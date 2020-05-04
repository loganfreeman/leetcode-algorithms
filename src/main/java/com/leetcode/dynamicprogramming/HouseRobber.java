package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/house-robber/

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * The key is to find the relation dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]).
 */
public class HouseRobber {
    public int rob(int[] points) {
        long rob = 0, notRob = 0;
        for (int i = 0; i < points.length; i++) {
            long currRob = notRob + points[i]; // selecting current element
            notRob = Math.max(notRob, rob); // not selecting current element
            rob = currRob;
        }
        return (int)Math.max(rob, notRob);
    }


    public int robDP(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;

        if(nums.length==1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);

        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }

        return dp[nums.length-1];
    }
}
