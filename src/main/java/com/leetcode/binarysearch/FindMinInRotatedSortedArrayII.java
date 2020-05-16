package com.leetcode.binarysearch;

/**
 *
 * This is a follow-up problem of finding minimum element in rotated sorted array without duplicate elements. We only need to add one more condition, which checks if the left-most element and the right-most element are equal. If they are we can simply drop one of them. In my solution below, I drop the left element whenever the left-most equals to the right-most.
 *
 */
public class FindMinInRotatedSortedArrayII {
    public int findMin(int[] num) {
        return findMin(num, 0, num.length-1);
    }

    public int findMin(int[] num, int left, int right){
        if(right==left){
            return num[left];
        }
        if(right == left +1){
            return Math.min(num[left], num[right]);
        }
        // 3 3 1 3 3 3

        int middle = (right-left)/2 + left;
        // already sorted
        if(num[right] > num[left]){
            return num[left];
            //right shift one
        }else if(num[right] == num[left]){
            return findMin(num, left+1, right);
            //go right
        }else if(num[middle] >= num[left]){
            return findMin(num, middle, right);
            //go left
        }else{
            return findMin(num, left, middle);
        }
    }

    public int findMinIterative(int[] nums) {
        int i=0;
        int j=nums.length-1;

        while(i<=j){

            //handle cases like [3, 1, 3]
            while(nums[i]==nums[j] && i!=j){
                i++;
            }

            if(nums[i]<=nums[j]){
                return nums[i];
            }

            int m=(i+j)/2;
            if(nums[m]>=nums[i]){
                i=m+1;
            }else{
                j=m;
            }
        }

        return -1;
    }
}
