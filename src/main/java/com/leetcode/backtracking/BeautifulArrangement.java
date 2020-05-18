package com.leetcode.backtracking;

public class BeautifulArrangement {
    int res = 0;
    public int countArrangement(int N) {
        int[] visited = new int[N];
        backtrack(N, 0, visited);
        return res;
    }

    public void backtrack(int N, int i, int[] visited) {
        if(i >= N) {
            ++res;
            return;
        }

        for(int j = 1; j <= N; j++) {
            if(visited[j-1] == 0) {
                if(j % (i+1) == 0 || (i+1) % j == 0) {
                    visited[j-1] = j;

                    backtrack(N, i+1, visited);

                    visited[j-1] = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
        int res = beautifulArrangement.countArrangement(3);
        System.out.println(res);
    }
}
