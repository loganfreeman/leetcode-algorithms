package com.leetcode.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 *
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *
 *
 * Example 2:
 *
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {
    // 对于求子数组的问题，需要时刻记着求累积和是一种很犀利的工具，但是这里怎么将子数组的和跟0和1的个数之间产生联系呢？这里需要用到一个 trick，遇到1就加1，遇到0，就减1，这样如果某个子数组和为0，就说明0和1的个数相等
    // 知道了这一点，就用一个 HashMap 建立子数组之和跟结尾位置的坐标之间的映射
    public int findMaxLength(int[] nums) {
        int res = 0, n = nums.length, sum = 0;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);

        for (int i = 0; i < n; ++i) {
            sum += (nums[i] << 1) -1; // sum += (nums[i] == 1) ? 1 : -1;
            if (m.containsKey(sum)) {
                // 如果某个子数组之和在 HashMap 里存在了，说明当前子数组减去 HashMap 中存的那个子数字，得到的结果是中间一段子数组之和，必然为0，说明0和1的个数相等，更新结果 res
                res = Math.max(res, i - m.get(sum));
            } else {
                m.put(sum, i);
            }
        }
        return res;
    }
}

