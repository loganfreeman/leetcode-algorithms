package com.leetcode.dynamicprogramming;
// https://leetcode.com/problems/decode-ways/

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.
 */
public class DecodeWays {
    public int numDecodings(String s) {

        char[] digits = s.toCharArray();

        int n = digits.length;
        // A table to store results of subproblems
        int count[] = new int[n + 1];
        count[0] = 1; // zero digit considered having one encoding
        count[1] = 1; // one digit having one encoding

        if(digits[0]=='0')   //for base condition "01123" should return 0
            return 0;

        for (int i = 2; i <= n; i++)
        {

            count[i] = 0;


            // If the last digit is not 0,
            // then last digit must add to
            // the number of words
            if (digits[i - 1] > '0')
                count[i] = count[i - 1];

            // If second last digit is smaller
            // than 2 and last digit is smaller
            // than 7, then last two digits
            // form a valid character
            if ((digits[i - 2] == '2'  && digits[i - 1] < '7')
                    ||
                    digits[i - 2] == '1'
            )
                count[i] += count[i - 2];
        }

        return count[n];




    }
}
