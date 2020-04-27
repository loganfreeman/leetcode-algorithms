package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/longest-palindromic-substring/

/*
Given a string s, find the longest palindromic substring in s.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        Manacher m = new Manacher(s);
        return m.longestPalindromicSubstring();

    }

    // https://medium.com/hackernoon/manachers-algorithm-explained-longest-palindromic-substring-22cb27a5e96f
    public class Manacher {
        private int[]  p;  // p[i] = length of longest palindromic substring of t, centered at i
        private String s;  // original string
        private char[] t;  // transformed string

        public Manacher(String s) {
            this.s = s;
            preprocess();
            p = new int[t.length];

            int center = 0, right = 0;
            for (int i = 1; i < t.length-1; i++) {
                int mirror = 2*center - i;

                if (right > i)
                    p[i] = Math.min(right - i, p[mirror]);

                // attempt to expand palindrome centered at i
                while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
                    p[i]++;

                // if palindrome centered at i expands past right,
                // adjust center based on expanded palindrome.
                if (i + p[i] > right) {
                    center = i;
                    right = i + p[i];
                }
            }

        }

        // Transform s into t.
        // For example, if s = "abba", then t = "$#a#b#b#a#@"
        // the # are interleaved to avoid even/odd-length palindromes uniformly
        // $ and @ are prepended and appended to each end to avoid bounds checking
        private void preprocess() {
            t = new char[s.length()*2 + 3];
            t[0] = '$';
            t[s.length()*2 + 2] = '@';
            for (int i = 0; i < s.length(); i++) {
                t[2*i + 1] = '#';
                t[2*i + 2] = s.charAt(i);
            }
            t[s.length()*2 + 1] = '#';
        }

        // longest palindromic substring
        public String longestPalindromicSubstring() {
            int length = 0;   // length of longest palindromic substring
            int center = 0;   // center of longest palindromic substring
            for (int i = 1; i < p.length-1; i++) {
                if (p[i] > length) {
                    length = p[i];
                    center = i;
                }
            }
            return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
        }


    }


    public static void main(String[] args) {

    }
}
