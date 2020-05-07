package com.leetcode.depthfirstsearch;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 *
 * A stack can be used to track and reduce pairs. Since the problem asks for length, we can put the index into the stack along with the character. For coding simplicity purpose, we can use 0 to respresnt ( and 1 to represent ).
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        Stack<int[]> stack = new Stack<>();
        int result = 0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c==')'){
                if(!stack.isEmpty() && stack.peek()[0]==0){
                    stack.pop();
                    result = Math.max(result, i-(stack.isEmpty()?-1:stack.peek()[1]));
                }else{
                    stack.push(new int[]{1, i});
                }
            }else{
                stack.push(new int[]{0, i});
            }
        }

        return result;
    }

    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (map.keySet().contains(curr)) {
                stack.push(curr);
            } else if (map.values().contains(curr)) {
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
