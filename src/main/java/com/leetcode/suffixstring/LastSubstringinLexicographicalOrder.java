package com.leetcode.suffixstring;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string s, return the last substring of s in lexicographical order.
 */
public class LastSubstringinLexicographicalOrder {
    // brute force version
    public String lastSubstring1(String str) {
        // loop to find the max leicographic
        // substring in the substring array
        String mx = "";
        for (int i = 0; i < str.length(); ++i) {
            if (mx.compareTo(str.substring(i)) <= 0) {
                mx = str.substring(i);
            }
        }

        return mx;
    }

    /**
     *
     * Key observation: The last substring must be a suffix of the original string, can’t a substring in the middle since we can always extend it.
     * e.g. leetcode -> tcode, can’t be “t”, “tc”, “tco”, “tcod”
     *
     * Find the first largest letter as a starting point, whenever there is a same letter, keep it as a candidate and compare with the current best. If the later is larger, take over the current best.
     *
     * @param s
     * @return
     */
    public String lastSubstring3(String s) {
        int max_i = 0; // index of max character found so far
        int max_c = 0; // max character so far
        int cand_i = 0;
        int l = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) > max_c) {
                // Find a better starting point.
                max_c = s.charAt(i);
                max_i = i;
                cand_i = -1;
                l = 1;
            } else if (s.charAt(i) == max_c) {
                // Find a new candidate, starting with length 1.
                cand_i = i;
                l = 1;
            } else if (cand_i != -1) {
                // Has a candidate.
                if (s.charAt(i) > s.charAt(max_i + l)) {
                    // The candidate is larger.
                    max_i = cand_i;
                    cand_i = -1;
                } else if (s.charAt(i) == s.charAt(max_i + l)) {
                    // The candidate is the same as current best.
                    ++l;
                } else {
                    // The candidate is smaller, no longer needed.
                    cand_i = -1;
                }
            }
        }

        return s.substring(max_i);

    }



    public static void main(String[] args) {
        LastSubstringinLexicographicalOrder solution = new LastSubstringinLexicographicalOrder();
        System.out.println(solution.lastSubstring2("cacacb"));
    }

    public String lastSubstring2(String str) {
        char maxchar = 'a';
        List<Integer> indices = new ArrayList<>();

        // we store all the indexes of maximum characters we have in the string
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > maxchar) {
                maxchar = str.charAt(i);
                indices.add(i);
            }
        }
        String maxstring = "";

        // We form a substring from that maximum
        // character index till end and check if
        // its greater that maxstring
        for (int i = 0; i < indices.size(); i++) {
            if (str.substring(indices.get(i)).compareTo(maxstring) > 0) {
                maxstring = str.substring(indices.get(i));
            }
        }
        return maxstring;

    }

    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length() - index;
        }
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index);
        }
    }



}
