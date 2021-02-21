package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * 解题思路
 * 1.动态规划1，使用
 * A。最近重复子问题：f(i, j) = min(f(i - 1, j), f(i, j - 1)) + f(i, j)
 * B。状态数组：f(i, j)
 * C。dp方程：f(i, j) = min(f(i - 1, j), f(i, j - 1)) + f(i, j)
 * @see #minPathSum_1(int[][])
 *
 *
 * @author : tanyu
 * create at:  2021-02-21  16:15
 * @description: 最小路径和
 */
public class MinPathSum {

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1) 使用本省二维数组进行处理
     *
     *
     * @param grid
     * @return
     */
    public int minPathSum_1(int[][] grid) {
        // 入参异常处理
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 从尾部向上递推，所以公式为：f(i, j) = min(f(i - 1, j), f(i, j - 1)) + f(i, j)
        // 对行进行遍历
        for (int i = 0; i < grid.length; i++) {
            // 对列进行遍历
            for (int j = 0; j < grid[0].length; j++) {
                // 首个节点不进行处理
                if (i == 0 && j==0) {
                    continue;
                } else if (i == 0) {
                    // 第一行直接向列获取即可
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (j == 0) {
                    // 第一列直接向下获取
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else {
                    // 其他的按照dp方程式进行处理
                    grid[i][j] = Math.min(grid[i - 1][j],grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}