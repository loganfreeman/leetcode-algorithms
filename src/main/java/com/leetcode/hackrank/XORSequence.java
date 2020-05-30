package com.leetcode.hackrank;

import java.util.HashMap;
import java.util.Map;

public class XORSequence {
    /*
     * Complete the xorSubsequence function below.
     */
    static int[] xorSubsequence(long[] set) {
        int n = set.length;

        Map<Long, Integer> map = new HashMap<Long, Integer>();

        int[] results = new int[1<<n];
        //
        // Run a loop for printing all 2^n
        // subsets one by one
        for(int i = 0; i< (1<<n); i++) {

            long res = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    res = res ^ set[j];
                }

            }

            if(!map.containsKey(res)) {
                map.put(res, 1);
                results[i] = 1;
            } else {
                map.put(res, map.get(res) + 1);
                results[i] = map.get(res) + 1;
            }






        }

        return results;
    }


}
