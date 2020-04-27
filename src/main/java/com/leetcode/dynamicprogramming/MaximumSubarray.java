package com.leetcode.dynamicprogramming;
// https://www.baeldung.com/java-maximum-subarray

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

maximumSubArraySum[i] = Max(arr[i], maximumSubArraySum[i-1] + arr[i])
 */
public class MaximumSubarray {
    public int maxSubArraySum(int[] arr) {

        int size = arr.length;
        int start = 0;
        int end = 0;

        int maxSoFar = 0, maxEndingHere = 0;

        for (int i = 0; i < size; i++) {
            if (arr[i] > maxEndingHere + arr[i]) {
                start = i;
                maxEndingHere = arr[i];
            } else
                maxEndingHere = maxEndingHere + arr[i];

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                end = i;
            }
        }
        return maxSoFar;
    }
}

