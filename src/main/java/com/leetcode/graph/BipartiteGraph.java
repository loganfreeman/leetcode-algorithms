package com.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 */
public class BipartiteGraph {
    public boolean isBipartite(int[][] graph) {
        int node = graph.length, edge = graph[0].length;
        int[] colors = new int[node];

        // Dye and Check all nodes
        for (int i = 0; i < node; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int curr, int color) {
        colors[curr] = color;
        for (int neigh : graph[curr]) {
            // if the neigh node has the same color, then return false
            if (colors[neigh] == colors[curr]) {
                return false;
            }
            // if the neigh node hasn't been dyed, check it can be dyed successfully
            if (colors[neigh] == 0 && !dfs(graph, colors, neigh, -color)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Following is a simple algorithm to find out whether a given graph is Birpartite or not using Breadth First Search (BFS).
     * 1. Assign RED color to the source vertex (putting into set U).
     * 2. Color all the neighbors with BLUE color (putting into set V).
     * 3. Color all neighbor’s neighbor with RED color (putting into set U).
     * 4. This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
     * 5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)
     * @param graph
     * @return
     */
    public boolean isBipartite2(int[][] graph) {
        int colorArr[] = new int[graph.length];
        for (int i = 0; i < graph.length; ++i)
            colorArr[i] = -1;

        // This code is to handle disconnected graoh
        for (int i = 0; i < graph.length; i++)
            if (colorArr[i] == -1)
                if (isBipartiteUtil(graph, i, colorArr) == false)
                    return false;

        return true;
    }

    public static boolean isBipartiteUtil(int G[][], int src,
                                          int colorArr[])
    {
        colorArr[src] = 1;

        // Create a queue (FIFO) of vertex numbers and
        // enqueue source vertex for BFS traversal
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(src);

        // Run while there are vertices in queue
        // (Similar to BFS)
        while (!q.isEmpty())
        {
            // Dequeue a vertex from queue
            // ( Refer http://goo.gl/35oz8 )
            int u = q.poll();

            // Return false if there is a self-loop
            if (G[u][u] == 1)
                return false;

            // Find all non-colored adjacent vertices
            for (int v = 0; v < G.length; ++v)
            {
                // An edge from u to v exists and
                // destination v is not colored
                if (G[u][v] ==1 && colorArr[v] == -1)
                {
                    // Assign alternate color to this
                    // adjacent v of u
                    colorArr[v] = 1 - colorArr[u];
                    q.add(v);
                }

                // An edge from u to v exists and
                // destination v is colored with same
                // color as u
                else if (G[u][v] ==1 && colorArr[v] ==
                        colorArr[u])
                    return false;
            }
        }

        // If we reach here, then all adjacent vertices
        // can be colored with alternate color
        return true;
    }

    public static void main(String[] args)
    {
        int G[][] = {{0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};

        BipartiteGraph bipartiteGraph = new BipartiteGraph();

        if (bipartiteGraph.isBipartite(G))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

}
