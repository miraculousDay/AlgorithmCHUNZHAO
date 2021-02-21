package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * 解题思路
 * 1.动态规划
 * A。最近重复子问题：当前f(i, j)为1时，只需要关注其坐、上、左上三个的值，如果最小都为1，则可以形成一个正方形，否则不行
 * 所以f(i, j) = min(f(i, j - 1),f(i - 1, j),f(i - 1, j - 1)) + 1
 * B。状态数组 int[][]
 * C。dp方程式：f(i, j) = min(f(i, j - 1),f(i - 1, j),f(i - 1, j - 1)) + 1
 *
 * @author : tanyu
 * create at:  2021-02-21  21:36
 * @description: 最大正方形
 */
public class MaximalSquare {

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     *
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        // 正方形的宽度
        int result = 0;
        // 状态数组
        int[][] dp = new int[row + 1][col + 1];
        // 从1开始遍历，0为dp默认值
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 当字符为1，则判断坐、上、左上的最小值，当最小值都为1的时候，则表示可以称形成正方形
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j],
                            dp[i][j - 1]), dp[i -1][j - 1]) + 1;
                    // 正方形的宽度
                    result = Math.max(dp[i][j], result);
                }
            }
        }
        return result * result;
    }
}