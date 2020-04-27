package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/regular-expression-matching/

/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int i, int j) {
        if (i >= s.length() && j >= p.length()) {
            return true;
        }


        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            if (helper(s, p, i, j + 2)) {
                return true;
            }

            if (p.charAt(j) == '.') {
                for (int k = i; k < s.length(); k++) {
                    if (helper(s, p, k + 1, j + 2)) {
                        return true;
                    }
                }
            } else {
                for (int k = i; k < s.length(); k++) {
                    if (s.charAt(k) == p.charAt(j)) {
                        if (helper(s, p, k + 1, j + 2)) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        } else if (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            return helper(s, p, i + 1, j + 1);
        }

        return false;
    }
}
