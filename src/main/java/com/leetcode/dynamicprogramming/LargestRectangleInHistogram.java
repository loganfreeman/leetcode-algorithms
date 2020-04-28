package com.leetcode.dynamicprogramming;
// https://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/

/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

If a bar is blocked by a lower bar, then the taller bar is no need to be considered any more. We only need to keep track of the bars that are not blocked. As we iterate over the bars, whenever a bar blocks a previous bar, we calculate how much area the previous bar can support.

The key to solve this problem is to maintain a stack to store bars' indexes. The stack only keeps the increasing bars.
 */


import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        int i = 0;

        while (i < height.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = height[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }

        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = height[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }

        return max;
    }
}
