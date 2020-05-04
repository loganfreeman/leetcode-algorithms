package com.leetcode.dynamicprogramming;
// https://www.programcreek.com/2014/05/leetcode-house-robber-ii-java/

/**
 * This is an extension of House Robber. There are two cases here 1) 1st element is included and last is not included 2) 1st is not included and last is included. Therefore, we can use the similar dynamic programming approach to scan the array twice and get the larger value.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        if(nums.length==1)
            return nums[0];

        int max1 = robHelper(nums, 0, nums.length-2);
        int max2 = robHelper(nums, 1, nums.length-1);

        return Math.max(max1, max2);
    }

    public int robHelper(int[] nums, int i, int j){
        if(i==j){
            return nums[i];
        }

        int[] dp = new int[nums.length];
        dp[i]=nums[i];
        dp[i+1]=Math.max(nums[i+1], dp[i]);

        for(int k=i+2; k<=j; k++){
            dp[k]=Math.max(dp[k-1], dp[k-2]+nums[k]);
        }

        return dp[j];
    }
}

