package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/interleaving-string/

/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 */
public class InterleavingString {
    // brute force, recursive
    public boolean is_Interleave(String s1,int i,String s2,int j,String res,String s3)
    {
        if(res.equals(s3) && i==s1.length() && j==s2.length())
            return true;
        boolean ans=false;
        if(i<s1.length())
            ans|=is_Interleave(s1,i+1,s2,j,res+s1.charAt(i),s3);
        if(j<s2.length())
            ans|=is_Interleave(s1,i,s2,j+1,res+s2.charAt(j),s3);
        return ans;

    }
    public boolean isInterleaveBruteForce(String s1, String s2, String s3) {
        return is_Interleave(s1,0,s2,0,"",s3);
    }

    /**
     * irrespective of the order of processing, if the resultant string formed till now is matching with the required string(s3s3), the final result is dependent only on the remaining portions of s1s1 and s2s2, but not on the already processed portion.
       The recursive approach includes a character from one of the strings s1s1 or s2s2 in the resultant interleaved string and called a recursive function to check whether the remaining portions of s1s1 and s2s2 can be interleaved to form the remaining portion of s3s3.
     */
    public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        // branching, recursion
        if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        // memorization
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean isInterleaveRecursiveWithMemo(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
    }

    // To implement this DP method, we'll make use of a 2D boolean array dpdp. In this array dp[i][j]dp[i][j] implies if it is possible to obtain a substring of length (i+j+2)(i+j+2) which is a prefix of s3s3 by some interleaving of prefixes of strings s1s1 and s2s2 having lengths (i+1)(i+1) and (j+1)(j+1) respectively.
    public boolean isInterleaveDP(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    // base case, return true
                    dp[i][j] = true;
                } else if (i == 0) {
                    // when s1 is empty
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    // when s2 is empty
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
