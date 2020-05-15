package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class GenerateParenthesis {
    public void generateParenthesis(List<String> results, String current, int open, int close, int n) {
        if(close == n) {
            if(current.length() > 0)
                results.add(current);
        } else {
            if(open < n) {
                String next = current + '(';
                generateParenthesis(results, next, open+1, close, n);
            }
            if(open > close) {
                String next = current + ')';
                generateParenthesis(results, next, open, close+1, n);
            }
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        generateParenthesis(results,"", 0, 0, n );
        return results;
    }
}
