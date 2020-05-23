package com.leetcode.graph;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {
    // Bellman–Ford algorithm
    // The Bellman–Ford algorithm is an algorithm that computes shortest paths from a single source vertex to all of the other vertices in a weighted digraph.[1] It is slower than Dijkstra's algorithm for the same problem, but more versatile, as it is capable of handling graphs in which some of the edge weights are negative numbers.

    public int networkDelayTime(int[][] times, int N, int K) {
        int MAX_TIME = 101 * 100;
        int[] dist = new int[N];
        for(int i = 0; i < N; i++) {
            dist[i] = MAX_TIME;
        }
        dist[K-1]= 0;
        for(int i = 1; i < N; i++) {
            for(int[] time : times) {
                int u = time[0]-1, v = time[1] -1, w = time[2];
                dist[v]= Math.min(dist[v], dist[u]+w);
            }
        }

        int max_dist = 0;
        for(int i = 0; i < N; i++) {
            max_dist = Math.max(dist[i], max_dist);
        }
        return max_dist == MAX_TIME ? -1 : max_dist;
    }

}
