package com.leetcode.dynamicprogramming;
// https://www.programcreek.com/2014/05/leetcode-paint-house-java/

/**
 *
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if(costs==null||costs.length==0){
            return 0;
        }

        int[][] matrix = new int[3][costs.length];

        for(int j=0; j<costs.length; j++){
            if(j==0){
                matrix[0][j]=costs[j][0];
                matrix[1][j]=costs[j][1];
                matrix[2][j]=costs[j][2];
            }else{
                matrix[0][j]=Math.min(matrix[1][j-1], matrix[2][j-1])+costs[j][0];
                matrix[1][j]=Math.min(matrix[0][j-1], matrix[2][j-1])+costs[j][1];
                matrix[2][j]=Math.min(matrix[0][j-1], matrix[1][j-1])+costs[j][2];
            }
        }

        int result = Math.min(matrix[0][costs.length-1], matrix[1][costs.length-1]);
        result = Math.min(result, matrix[2][costs.length-1]);

        return result;
    }
}
