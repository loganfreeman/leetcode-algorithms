package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinationsofaPhoneNumber {
    // table[i] stores all characters that
    // corresponds to ith digit in phone
    String[] table = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void combination(String digits, int index, String current, List<String> results) {


        if (index == digits.length()) {
            if (current.length() > 0)
                results.add(current);
        } else {
            int num = digits.charAt(index) - '0';
            String characters = table[num];
            for (int i = 0; i < characters.length(); i++) {
                char c = characters.charAt(i);
                String next = current + c;
                combination(digits, index + 1, next, results);
            }

        }
    }

    public List<String> letterCombinations(String digits) {

        List<String> results = new ArrayList<String>();
        combination(digits, 0, "", results);
        return results;
    }
}
