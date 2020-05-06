package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/maximum-product-subarray/

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * When iterating the array, each element has two possibilities: positive number or negative number. We need to track a minimum value, so that when a negative number is given, it can also find the maximum value. We define two local variables, one tracks the maximum and the other tracks the minimum.
 */
public class MaximumProductSubarray {
    // Function to find maximum product subarray
    int maxProduct(int arr[], int n)
    {

        // Variables to store maximum and minimum
        // product till ith index.
        int minVal = arr[0];
        int maxVal = arr[0];

        int maxProduct = arr[0];

        for (int i = 1; i < n; i++)
        {

            // When multiplied by -ve number,
            // maxVal becomes minVal
            // and minVal becomes maxVal.
            if (arr[i] < 0)
            {
                int temp = maxVal;
                maxVal = minVal;
                minVal =temp;

            }

            // maxVal and minVal stores the
            // product of subarray ending at arr[i].
            maxVal = Math.max(arr[i], maxVal * arr[i]);
            minVal = Math.min(arr[i], minVal * arr[i]);

            // Max Product of array.
            maxProduct = Math.max(maxProduct, maxVal);
        }

        // Return maximum product found in array.
        return maxProduct;
    }
}
