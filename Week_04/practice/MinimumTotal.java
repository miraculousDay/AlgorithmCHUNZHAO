package practice;

import java.util.List;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/triangle/
 *
 * 解题思略
 * 1。动态规划，该题目是向相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点移动，
 * 则找到子问题为 f(i, j) = min(f(i + 1, j), f(i + 1, j + 1)) + f(i ,j)
 * dp方程则为：f(i, j) = min(f(i + 1, j), f(i + 1, j + 1)) + f(i ,j)
 * 状态数组为：f(i, j)
 * @see #minimumTotal_1(List)
 *
 * 2。动态规划(简化空间)
 * @see #minimumTotal_2(List)
 *
 * 3.分治(递归)
 * @see #minimumTotal_3(List)
 *
 * @author : tanyu
 * create at:  2021-02-21  12:12
 * @description: 三角形最小路径和
 */
public class MinimumTotal {

    /**
     * 时间复杂度：O(2n)
     * 空间复杂度：O(n2)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_3(List<List<Integer>> triangle) {
        // 缓存
        int[][] memo = new int[triangle.size()][triangle.size()];
        // 递归主体
        return helper(0, 0, triangle, memo);
    }

    public int helper(int row, int col, List<List<Integer>> triangle, int[][] memo) {
        // 退出条件
        if (row == triangle.size()) {
            return 0;
        }
        // 检查缓存
        if (memo[row][col] != 0) {
            return memo[row][col];
        }
        // 拆分子问题，计算后将结果放入缓存
        return memo[row][col] = Math.min(helper(row + 1, col, triangle, memo),
                helper(row + 1, col + 1, triangle, memo)) + triangle.get(row).get(col);
    }

    /**
     * 时间复杂度：O(n2)
     * 空间复杂度：O(n2)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_1(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 状态数组
        int[][] dp = new int[n + 1][n + 1];
        // 从最后一行向上遍历
        for (int i = n - 1; i >= 0; i--) {
            // 对该行元素进行遍历
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // dp方程式进行计算
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
     }

    /**
     * 时间复杂度：O(n2)
     * 空间复杂度：O(n)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_2(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 计算中只需要用到row + 1 行的数据，则可使用一维数组即可
        int[] dp = new int[n + 1];
        // 从最后一行开始计算
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 运用dp方程式计算
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
     }
}