package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SplitStringIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        helper(res, S);
        return res;
    }

    public boolean helper(List<Integer> res, String S) {
        if(res.size() > 2 && S.length() == 0) return true;

        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(0) == '0' && i > 0) break;
            long val = Long.valueOf(S.substring(0, i + 1));
            int size = res.size();
            if (val > Integer.MAX_VALUE)
                break;
            if (size >= 2 && val > res.get(size - 1) + res.get(size - 2))
                break;
            if (size < 2 || val == res.get(size - 1) + res.get(size - 2)) {
                res.add((int)val);
                if (helper(res, S.substring(i + 1))) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }

        return false;
    }
}
