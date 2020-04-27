package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/climbing-stairs/

/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] steps = new int[n+1];
        steps[0] = 0;
        steps[1] = 1;
        steps[2] = 2;
        for(int i = 3; i <= n; i++) {
            steps[i] = steps[i-2] + steps[i-1];
        }

        return steps[n];
    }
}
