package com.leetcode.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 */
public class KSimilarStrings {
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    /**
     * We want to transform B into A, let i be the first index that A[i]!=B[i], and we need to find j so that B[j]==A[i] where i<j and B[j]!=A[j]. Then we swap B[i] and B[j], plus one to the step we take, and we are one or two steps closer to the target A. The whole process could be visualized in a search tree space.
     * @param A
     * @param B
     * @return
     */
    public int kSimilarity(String A, String B) {
        int res = 0, n = A.length();
        Queue<char[]> q = new LinkedList<>();
        q.add(A.toCharArray());
        Set<char[]> visited = new HashSet<>();
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                char[] charArray = q.peek(); q.poll();
                if (charArray.toString().equals(B)) return res;
                int i = 0;
                while (i < n && charArray[i] == B.charAt(i)) ++i;
                for (int j = i + 1; j < n; ++j) {
                    if (charArray[j] == B.charAt(j) || charArray[j] != B.charAt(i)) continue;
                    swap(charArray, i, j);
                    if (!visited.contains(charArray)) {
                        visited.add(charArray);
                        q.add(charArray);
                    }
                    swap(charArray, i, j);
                }
            }
            ++res;
        }
        return -1;

    }
}
