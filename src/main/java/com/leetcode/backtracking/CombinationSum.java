package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 */
public class CombinationSum {
    public void generate(int[] candidates, int target, Set<List<Integer>> results, int i, int sum, List<Integer> list) {
        //System.out.println(sum+ " " + list + " " + i);
        if(sum == target) {
            results.add(list);
        } else if(i < candidates.length && sum < target) {
            int v = candidates[i];
            List<Integer> nl = new ArrayList<Integer>(list);
            nl.add(v);
            generate(candidates, target, results, i+1, sum+v, nl);
            generate(candidates, target, results, i+1, sum, list);
            generate(candidates, target, results, i, sum+v, nl);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> results = new HashSet<List<Integer>>();
        generate(candidates, target, results, 0, 0, new ArrayList<Integer>());
        return new ArrayList<List<Integer>>(results);
    }
}
