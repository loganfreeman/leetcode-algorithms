package com.leetcode.graph;

/**
 *
 * Given two strings of lowercase alphabets and a value k, the task is to find if two strings are K-anagrams of each other or not.
 *
 * Two strings are called k-anagrams if following two conditions are true.
 *
 * Both have same number of characters.
 * Two strings can become anagram by changing at most k characters in a string.
 */
public class kAnagrams {
    static final int MAX_CHAR = 26;
    /**
     * Stores occurrence of all characters of both strings in separate count arrays.
     * Count number of different characters in both strings (in this if a strings has 4 a and second has 3 ‘a’ then it will be also count.
     * If count of different characters is less than or equal to k, then return true else false.
     */
    static boolean arekAnagrams(String str1, String str2,
                                int k)
    {
        // If both strings are not of equal
        // length then return false
        int n = str1.length();
        if (str2.length() != n)
            return false;

        int[] count1 = new int[MAX_CHAR];
        int[] count2 = new int[MAX_CHAR];
        int count = 0;

        // Store the occurrence of all characters
        // in a hash_array
        for (int i = 0; i < n; i++)
            count1[str1.charAt(i) - 'a']++;
        for (int i = 0; i < n; i++)
            count2[str2.charAt(i) - 'a']++;

        // Count number of characters that are
        // different in both strings
        for (int i = 0; i < MAX_CHAR; i++)
            if (count1[i] > count2[i])
                count = count + Math.abs(count1[i] -
                        count2[i]);

        // Return true if count is less than or
        // equal to k
        return (count <= k);
    }
}
