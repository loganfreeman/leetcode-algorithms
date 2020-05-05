package com.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 *
 * This problem is converted to find the median value on x-axis and y-axis.
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;

        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    cols.add(j);
                    rows.add(i);
                }
            }
        }

        int sum=0;

        for(Integer i: rows){
            sum += Math.abs(i - rows.get(rows.size()/2));
        }

        Collections.sort(cols);

        for(Integer i: cols){
            sum+= Math.abs(i-cols.get(cols.size()/2));
        }

        return sum;
    }
}
