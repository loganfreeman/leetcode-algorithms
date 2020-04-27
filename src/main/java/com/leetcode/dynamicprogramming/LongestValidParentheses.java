package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/longest-valid-parentheses/

/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // in this case, res[i] means the max length of parensis ended at i; if not, then res[i] = 0
        int[] res = new int[s.length()];
        int open = 0, max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
                //res[i] = res[i-1];
            } else {
                if (open > 0) {
                    res[i] = res[i-1] + 2;
                    // important lines here
                    if (i >= res[i]) {
                        res[i] += res[i-res[i]];
                    }
                    open--;
                }
            }

            max = Math.max(max, res[i]);
        }

        return max;
    }
}
