package practice;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/unique-paths-ii/
 *
 * 解题思路
 * 1.动态规划
 * 由于我们每一步只能从向下或者向右移动一步，因此要想走到 (i, j)(i,j)，
 * 如果向下走一步，那么会从 (i-1, j)(i−1,j) 走过来；如果向右走一步，
 * 那么会从 (i, j-1)(i,j−1) 走过来。因此我们可以写出动态规划转移方程：
 * f(i, j) = f(i-1, j) + f(i, j-1)
 * 此题需要排除障碍物的位置，
 *
 * @see #uniquePathsWithObstacles_1(int[][])
 *
 * 2.分治
 * @see #uniquePathsWithObstacles_2(int[][])
 *
 * @author : tanyu
 * create at:  2021-02-18  22:53
 * @description: 不同路径 II
 */
public class UniquePathsWithObstacles {

    private static int[][] testData = new int[][]{{0,0,0},{0,1,0},{0,0,0}};

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles_1(testData));
    }

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        // 行
        int row = obstacleGrid.length;
        // 列
        int col = obstacleGrid[0].length;
        // 新数组保存空地信息
        int[][] dp = new int[row][col];
        // 处理第一列，不是阻碍物的则设置为1
        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        // 处理第一层，不是阻碍物的则设置为1
        for (int j = 0; j < col && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        return 1;
    }
}
