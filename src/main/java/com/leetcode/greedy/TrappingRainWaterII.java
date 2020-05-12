package com.leetcode.greedy;

// https://www.cnblogs.com/grandyang/p/5928987.html

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
 *
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 *
 * Example:
 *
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 *
 * Return 4.
 *
 *
 * 这道题是之前那道 Trapping Rain Water 的拓展，由 2D 变 3D 了，感觉很叼。但其实解法跟之前的完全不同了，之前那道题由于是二维的，我们可以用双指针来做，而这道三维的，我们需要用 BFS 来做，解法思路很巧妙
 *
 * 首先我们应该能分析出，能装水的底面肯定不能在边界上，因为边界上的点无法封闭，那么所有边界上的点都可以加入 queue，当作 BFS 的启动点，同时我们需要一个二维数组来标记访问过的点，访问过的点我们用红色来表示
 *
 * 我们再想想，怎么样可以成功的装进去水呢，是不是周围的高度都应该比当前的高度高，形成一个凹槽才能装水，而且装水量取决于周围最小的那个高度，有点像木桶原理的感觉，那么为了模拟这种方法，我们采用模拟海平面上升的方法来做，我们维护一个海平面高度 mx，初始化为最小值，从1开始往上升，那么我们 BFS 遍历的时候就需要从高度最小的格子开始遍历，那么我们的 queue 就不能使用普通队列了，而是使用优先级队列，将高度小的放在队首，最先取出，这样我们就可以遍历高度为1的三个格子，用绿色标记出来了
 *
 * 如上图所示，向周围 BFS 搜索的条件是不能越界，且周围格子未被访问
 *
 * 那么优先队列 queue 中高度为1的格子遍历完了，此时海平面上升1，变为2，此时我们遍历优先队列 queue 中高度为2的格子
 *
 * 我们发现这三个绿格子周围的格子均已被访问过了，所以不做任何操作，海平面继续上升，变为3，遍历所有高度为3的格子
 *
 * 由于我们没有特别声明高度相同的格子在优先队列 queue 中的顺序，所以应该是随机的，其实谁先遍历到都一样，对结果没啥影响，我们就假设第一行的两个绿格子先遍历到，那么那么周围各有一个灰格子可以遍历，这两个灰格子比海平面低了，可以存水了，把存水量算出来加入结果 res 中
 *
 *
 */
public class TrappingRainWaterII {
    int[] dx =  {0, -1, 0, 1};
    int[] dy = {-1, 0, 1, 0};
    public class Pair {
        public int height;
        public int index;
        public Pair(int height, int idx) {
            this.height = height;
            this.index = idx;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if(heightMap.length == 0) return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;

        int res = 0;
        int mx = 0;

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.height - o2.height;
            }
        });

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    q.offer(new Pair(heightMap[i][j], i * n + j));
                    visited[i][j] = true;
                }
            }
        }
        while(!q.isEmpty()) {
            Pair pair = q.poll();
            int h = pair.height;
            int r = pair.index/n;
            int c = pair.index % n;
            mx = Math.max(mx, h);
            for(int d = 0; d < 4 ; d++) {
                int x = r + dx[d];
                int y = c + dy[d];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
                visited[x][y] = true;
                if (heightMap[x][y] < mx) {
                    res += mx - heightMap[x][y];
                }
                q.offer(new Pair(heightMap[x][y], x * n + y));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] array = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        TrappingRainWaterII solution = new TrappingRainWaterII();
        int res = solution.trapRainWater(array);
        System.out.println(res);
    }
}
