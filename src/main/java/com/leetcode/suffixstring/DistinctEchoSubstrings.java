package com.leetcode.suffixstring;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).
 *
 *
 *
 * Example 1:
 *
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * Example 2:
 *
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 */
public class DistinctEchoSubstrings {
    // brute force + hash set
    public int distinctEchoSubstrings(String text) {
        Set<String> set = new HashSet();
        for(int i = 0; i < text.length() - 1; i++){
            for(int j = i + 2; j <= text.length(); j+=2){
                if(helper(text.substring(i, j)))
                    set.add(text.substring(i, j));
            }
        }
        return set.size();
    }
    public boolean helper(String s){
        int mid = s.length() / 2;
        return s.substring(0, mid).equals(s.substring(mid));
    }

}
