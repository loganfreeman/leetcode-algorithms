package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/edit-distance/

/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
 */

import java.util.Arrays;
import java.util.Collections;

// https://medium.com/@dakota.lillie/an-intro-to-dynamic-programming-pt-ii-edit-distance-ceed0b12fe6d
public class EditDistance {
    static int min(int insert, int remove, int replace) {
        Integer[] values = { insert, remove, replace };
        return Collections.min(Arrays.asList(values));
    }
    static int calculateEditDistance(String str1, String str2, int i, int j) {

        // If i is 0, we're essentially looking at an empty substring of str1.
        // To convert an empty substring to any other string, you just insert
        // every character from the other string. Since the only operation to be
        // performed is insertion, the cost is equal to the length of the other
        // string.
        if (i == 0) {
            return j;
        }

        // This goes the other way as well of course — if you need to get from
        // some substring to an empty string, The shortest path is to simply
        // remove every character in the substring.
        if (j == 0) {
            return i;
        }

        // If the characters in the strings at a particular value of i and j match,
        // then we don't need to perform any operation and the cost is 0. Since
        // Java is a 0-indexed language, we need to subtract 1, otherwise we'd
        // get a StringIndexOutOfBoundsException.
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            return calculateEditDistance(str1, str2, i - 1, j - 1);
        }

        // Otherwise, we need to recurse. Note that we're using Levenshtein
        // distance here.
        return min(1 + calculateEditDistance(str1, str2, i, j - 1),      // insert
                1 + calculateEditDistance(str1, str2, i - 1, j),      // remove
                2 + calculateEditDistance(str1, str2, i - 1, j - 1)); // substitute
    }

    static int calculateEditDistance(String str1, String str2, int i, int j, int[][] memo) {

        // A value of greater than -1 in the memo grid means that the value for
        // this combination of i and j has already been calculated and can be
        // promptly returned.
        if (memo[i][j] > -1) return memo[i][j];

        if (i == 0) {
            memo[i][j] = j;
            return j;
        }

        if (j == 0) {
            memo[i][j] = i;
            return i;
        }

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            memo[i][j] = calculateEditDistance(str1, str2, i - 1, j - 1, memo);
            return memo[i][j];
        }

        memo[i][j] = min(1 + calculateEditDistance(str1, str2, i, j - 1, memo),      // insert
                1 + calculateEditDistance(str1, str2, i - 1, j, memo),      // remove
                2 + calculateEditDistance(str1, str2, i - 1, j - 1, memo)); // substitute
        return memo[i][j];
    }

    static int calculateEditDistance(String str1, String str2) {

        int[][] memo = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    memo[i][j] = j;
                }
                else if (j == 0) {
                    memo[i][j] = i;
                }
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                }
                else {
                    memo[i][j] = min(1 + memo[i][j - 1],
                            1 + memo[i - 1][j],
                            2 + memo[i - 1][j - 1]);
                }
            }
        }

        return memo[str1.length()][str2.length()];
    }

}
