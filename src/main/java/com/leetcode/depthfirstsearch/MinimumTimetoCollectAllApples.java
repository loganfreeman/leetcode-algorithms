package com.leetcode.depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend in order to collect all apples in the tree starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.
 */
public class MinimumTimetoCollectAllApples {
    Map<Integer, List<Integer>> um = new HashMap<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        for(int[] edge: edges) {
            int from = edge[0], to = edge[1];
            if(um.get(from) == null) {
                um.put(from, new ArrayList<>());
            }
            um.get(from).add(to);
        }

        return solve(hasApple, 0);
    }

    public int solve(List<Boolean> hasApple, int start) {
        if(um.get(start) == null) return 0;

        int sum = 0;
        List<Integer> vertices = um.get(start);

        for(Integer v : vertices) {
            int tmp = solve(hasApple, v);
            if(tmp > 0 || hasApple.contains(v)) {
                sum += 2 + tmp;
            }
        }

        return sum;
    }
}
