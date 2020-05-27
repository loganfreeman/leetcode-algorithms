package com.leetcode.backtracking;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        return isValid(s, 0, n - 1, f);
    }
    // dynamic programming, backtracking
    public boolean isValid(String s, int start, int end, int[][] f) {
        if(start > end) return true;
        if(start == end) {
            boolean res = s.charAt(start) == '*';
            f[start][end] = res ? 1 : -1;
            return res;
        }

        if(f[start][end] != 0) return f[start][end] == 1; // is already memorized

        if((s.charAt(start) == '(' || s.charAt(start) == '*')
                && (s.charAt(end) == ')'|| s.charAt(end) == '*')
                && isValid(s, start + 1, end - 1, f)) {
            f[start][end] = 1;
            return true;

        }

        for(int i = start; i < end; i++){
            if(isValid(s, start, i, f) && isValid(s, i + 1, end, f)) {
                f[start][end] = 1;
                return true;
            }
        }

        f[start][end] = -1;

        return false;
    }

}
