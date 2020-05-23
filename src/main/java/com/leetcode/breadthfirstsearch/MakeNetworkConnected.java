package com.leetcode.breadthfirstsearch;

import java.util.HashSet;
import java.util.Set;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 *
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it’s not possible, return -1.
 *
 * Solution 1: Union-Find
 * Time complexity: O(V+E)
 * Space complexity: O(V)
 *
 * Solution 2: DFS
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 */
public class MakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1) return -1;
        int[] p = new int[n];
        for(int i = 0; i<n; i++) {
            p[i] = i;
        }
        for(int[] c : connections) {
            p[find(p, c[0])] = find(p, c[1]);
        }
        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < n; ++i)
            s.add(find(p, i));

        return s.size() - 1;
    }

    int find(int[] p, int x) {
        if(p[x] == x) return x;
        p[x] = find(p, p[x]);
        return p[x];
    }

    public static void main(String[] args) {
        int[][] connections = new int[][] {{0, 1}, {0, 2}, {1, 2}};
        MakeNetworkConnected makeNetworkConnected = new MakeNetworkConnected();
        int ans = makeNetworkConnected.makeConnected(4, connections);
        System.out.println(ans);
    }
}
