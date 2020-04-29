package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/distinct-subsequences/

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * It's guaranteed the answer fits on a 32-bit signed integer.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        if(m > n) return 0;

        int[][] mat = new int[m+1][n+1];


        // Initializing first column with
        // all 0s. An empty string can't have
        // another string as sub sequence
        for (int i = 1; i <= m; i++)
            mat[i][0] = 0;

        // Initializing first row with all 1s.
        // An empty string is sub sequence of all.
        for (int j = 0; j <= n; j++)
            mat[0][j] = 1;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(t.charAt(i-1) == s.charAt(j-1)) {
                    // case when the current char can be used in the sub sequence
                    mat[i][j] = mat[i][j - 1] + mat[i - 1][j - 1];
                } else {
                    // case when the current char cannot be used in the sub sequence
                    mat[i][j] = mat[i][j - 1];
                }
            }
        }

        return mat[m][n];
    }
}
