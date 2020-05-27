package com.leetcode.datastructures;

public class UnionFindSet {
    int[] parents;
    int[] ranks;
    public UnionFindSet(int n) {
        parents = new int[n+1];
        ranks = new int[n+1];
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    int Find(int id) {
        if(id != parents[id]) {
            parents[id] = Find(parents[id]);
        }

        return parents[id];
    }

    boolean Union(int u, int v) {
        int pu = Find(u);
        int pv = Find(v);
        if(pu == pv) return false;

        if(ranks[pu] > ranks[pv]) {
            parents[pv] = pu;
        } else if(ranks[pv] > ranks[pu]) {
            parents[pu] = pv;
        } else {
            parents[pu] = pv;
            ++ranks[pv];
        }
        return true;
    }
}
