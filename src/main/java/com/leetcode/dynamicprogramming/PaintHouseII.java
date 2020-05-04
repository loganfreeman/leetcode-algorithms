package com.leetcode.dynamicprogramming;
// https://www.programcreek.com/2014/05/leetcode-paint-house-ii-java/

/**
 *
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if(costs==null || costs.length==0)
            return 0;

        int preMin=0;
        int preSecond=0;
        int preIndex=-1;

        for(int i=0; i<costs.length; i++){
            int currMin=Integer.MAX_VALUE;
            int currSecond = Integer.MAX_VALUE;
            int currIndex = 0;

            for(int j=0; j<costs[0].length; j++){
                if(preIndex==j){
                    costs[i][j] += preSecond;
                }else{
                    costs[i][j] += preMin;
                }

                if(currMin>costs[i][j]){
                    currSecond = currMin;
                    currMin=costs[i][j];
                    currIndex = j;
                } else if(currSecond>costs[i][j] ){
                    currSecond = costs[i][j];
                }
            }

            preMin=currMin;
            preSecond=currSecond;
            preIndex =currIndex;
        }

        int result = Integer.MAX_VALUE;
        for(int j=0; j<costs[0].length; j++){
            if(result>costs[costs.length-1][j]){
                result = costs[costs.length-1][j];
            }
        }
        return result;
    }
}
