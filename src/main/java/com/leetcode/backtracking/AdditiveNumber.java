package com.leetcode.backtracking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Follow up:
 * How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length(); i ++) {
            for (int j = i + 1; j < num.length() - i + 1; j ++) {
                String first = num.substring(0, i);
                String second = num.substring(i, j);
                if (isValid(j, first, second, num)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(int start, String first, String second, String num) {
        if (start == num.length()) {
            return true;
        }
        if ((first.charAt(0) == '0' && first.length() > 1) || (second.charAt(0) == '0' && second.length() > 1)) {
            return false;
        }
        BigInteger f = new BigInteger(first);
        BigInteger s = new BigInteger(second);
        BigInteger sum = f.add(s);
        String sumStr = sum.toString();
        if (sumStr.length() + start > num.length()) {
            return false;
        }
        System.out.println("has following:" + first + " : " + second + " : " + start);
        if (!num.substring(start, start + sumStr.length()).equals(sumStr)) {
            return false;
        }
        return isValid(start + sumStr.length(), second, sumStr, num);

    }
    public boolean isAdditiveNumberIterative(String num) {
        for(int i = 1; i < num.length(); i++) {
            String s1 = num.substring(0, i);
            if(s1.length() > 1 && s1.charAt(0) == '0') break;
            for(int j = i + 1; j < num.length(); j++) {
                String s2 = num.substring(i,j);
                long d1 = Long.parseLong(s1);
                long d2 = Long.parseLong(s2);
                if(s2.length() > 1 && s2.charAt(0) == '0') break;
                Long next = d1 + d2;
                String nextStr = next.toString();
                if(nextStr != num.substring(j, Math.min(j + nextStr.length(), num.length()))) continue;
                String allStr = s1 + s2 + nextStr;
                while(allStr.length()< num.length()) {
                    d1 = d2;
                    d2 = next;
                    next = d1 + d2;
                    nextStr = next.toString();
                    allStr += nextStr;
                }
                if(allStr.equals(num)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
        boolean result = additiveNumber.isAdditiveNumber("112358");
        System.out.println(result);
    }
}
