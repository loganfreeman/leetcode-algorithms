package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/word-break/

import java.util.Arrays;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class WordBreak {
    // Function to determine if String can be segmented into a space-
    // separated sequence of one or more dictionary words
    public boolean wordBreak(List<String> dict, String str,
                             int[] lookup)
    {
        // n stores length of current substring
        int n = str.length();

        // return true if we have reached the end of the String
        if (n == 0)
            return true;

        // if sub-problem is seen for the first time
        if (lookup[n] == -1)
        {
            // mark sub-problem as seen (0 initially assuming String
            // can't be segmented)
            lookup[n] = 0;

            // branching
            for (int i = 1; i <= n; i++)
            {
                // consider all prefixes of current String
                String prefix = str.substring(0, i);

                // if prefix is found in dictionary, then recur for suffix
                // recursion
                if (dict.contains(prefix) &&
                        wordBreak(dict, str.substring(i), lookup))
                {
                    // return true if the String can be segmented
                    // memorization
                    lookup[n] = 1;
                    return true;
                }
            }
        }

        // return solution to current sub-problem
        return lookup[n] == 1;
    }
    public boolean wordBreak(String str, List<String> dict) {
        // look-up array to store solutions to sub-problems
        // lookup[i] stores if substring str[n-i..n) can be segmented or not
        // set up memorization data structure, usually a array array of array
        int[] lookup = new int[str.length() + 1];
        Arrays.fill(lookup, -1);
        return wordBreak(dict, str, lookup);
    }
}
