package com.leetcode.unionfind;

/**
 *
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) return 0;

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (M[i][i] == 0) continue;
            ++ans;
            dfs(M, i, n);
        }
        return ans;
    }

    private void dfs(int[][] M, int curr, int n) {
        for (int i = 0; i < n; ++i) {
            if (M[curr][i] == 0) continue;
            M[curr][i] = M[i][curr] = 0;
            dfs(M, i, n);
        }
    }
}
