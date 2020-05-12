package com.leetcode.depthfirstsearch;

import java.util.*;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededToInformAllEmployees {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int ret = 0;
        int[] time = new int[n];
        int root = -1;
        for(int i = 0; i < manager.length; i++){
            int u = manager[i];
            int v = i;
            if(u == -1) {
                root = v;
                continue;
            }
            if(graph.get(u) == null) {
                graph.put(u, new ArrayList<>());
            }
            graph.get(u).add(i);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            if(graph.get(curr) == null) continue;
            for(int e : graph.get(curr)) {
                stack.push(e);
                time[e] = time[curr] + informTime[curr];
            }
        }
        for(int i = 0; i <n; i++)ret = Math.max(ret, time[i]);
        return ret;
    }
}
