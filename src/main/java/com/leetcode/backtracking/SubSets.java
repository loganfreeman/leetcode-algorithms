package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given a set of distinct integers, S, return all possible subsets.
 */
public class SubSets {
    /**
     * Given a set S of n distinct integers, there is a relation between Sn and Sn-1.
     * The subset of Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}.
     * Therefore, a Java solution can be quickly formalized.
     */

    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        if (S == null)
            return null;

        Arrays.sort(S);

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < S.length; i++) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

            //get sets that are already in result
            for (ArrayList<Integer> a : result) {
                temp.add(new ArrayList<Integer>(a));
            }

            //add S[i] to existing sets
            for (ArrayList<Integer> a : temp) {
                a.add(S[i]);
            }

            //add S[i] only as a set
            ArrayList<Integer> single = new ArrayList<Integer>();
            single.add(S[i]);
            temp.add(single);

            result.addAll(temp);
        }

        //add empty set
        result.add(new ArrayList<Integer>());

        return result;
    }

    /**
     * use recursion
     *
     * start from an empty set, adding the element one by one
     *
     * @param list
     * @param resultList
     * @param nums
     * @param start
     */
    private void subsetsHelper(List<List<Integer>> list , List<Integer> resultList, int [] nums, int start){
        list.add(new ArrayList<>(resultList));
        for(int i = start; i < nums.length; i++){
            // add element
            resultList.add(nums[i]);
            // Explore
            subsetsHelper(list, resultList, nums, i + 1);
            // remove
            resultList.remove(resultList.size() - 1);
        }
    }

    /**
     * The total number of subsets of any given set is equal to 2^ (no. of elements in the set).
     * @param set
     */
    static void printSubsets(char set[]) {
        int n = set.length;
        //
        // Run a loop for printing all 2^n
        // subsets one by one
        for(int i = 0; i< (1<<n); i++) {
            System.out.print("{");
            // Print current subset
            for (int j = 0; j < n; j++)

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    System.out.print(set[j] + " ");
            System.out.println("}");

        }
    }

    public static void main(String[] args)
    {
        char set[] = {'a', 'b', 'c'};
        printSubsets(set);
    }
}
