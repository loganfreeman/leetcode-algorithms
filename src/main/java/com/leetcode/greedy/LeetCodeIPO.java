package com.leetcode.greedy;

import java.util.PriorityQueue;

/**
 *
 * Assume that LeetCode is about to start its IPO. In order to sell shares to venture capital firms at a higher price, LeetCode hopes to launch some projects to increase its capital before the IPO. Due to limited resources, it can only complete up to k different projects before the IPO. Help LeetCode design the way to get the maximum total capital after completing up to k different projects.
 *
 * Given a number of projects. For each project i, it has a net profit Pi and requires a minimum capital Ci to launch the corresponding project. Initially, you have W capital. When you complete a project, you will earn a net profit and the profit will be added to your total capital.
 *
 * In summary, select a list of up to k different items from a given project to maximize the final capital and output the most capital that is ultimately available.
 *
 * Example 1:
 * Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 *
 * Output: 4
 *
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 *              After finishing it you will obtain profit 1 and your capital becomes 1.
 *              With capital 1, you can either start the project indexed 1 or the project indexed 2.
 *              Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 *              Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 */
public class LeetCodeIPO {
    private class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] node = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            node[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);

        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>((n1, n2) -> -(n1.p - n2.p));
        for (int j = 0; j < node.length; j++) {
            minCostQ.add(node[j]);
        }

        for (int m = 0; m < k; m++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }

            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}
