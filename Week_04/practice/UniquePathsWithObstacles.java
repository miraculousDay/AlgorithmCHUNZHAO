package practice;

import java.util.HashMap;
import java.util.Map;

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
 * 3.动态规划(简化形式)
 * @see #uniquePathsWithObstacles_3(int[][])
 *
 * @author : tanyu
 * create at:  2021-02-18  22:53
 * @description: 不同路径 II
 */
public class UniquePathsWithObstacles {

    private static int[][] testData = new int[][]{{0,0},{1,1},{0,0}};

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles_2(testData));
    }

    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(n)
     *
     * 此处是按照从上到下的每一行进行演算，每一行对应的移动步数就是1，当碰到障碍物的时候，则置为0
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles_3(int[][] obstacleGrid) {
        // 获取列数
        int col = obstacleGrid[0].length;
        // 创建数组
        int[] colArr = new int[col];
        // 初始化第一行的值为1
        colArr[0] = 1;
        // 按照行数进行遍历
        for (int[] row : obstacleGrid) {
            // 遍历对应的行的值
            for (int j = 0; j < col; j++) {
                // 当碰到障碍物的时候，则把colArr的值置为0
                if (row[j] == 1) {
                    colArr[j] = 0;
                } else if (j > 0) {
                    // j > 0 时候，则需要将上和左的步数相加
                    colArr[j] += colArr[j - 1];
                }
            }
        }
        return colArr[col - 1];
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
                // 不是障碍物则f(i, j) = f(i + 1, j) + f(i, j + 1)
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * 时间复杂度：O(2max(m,n))
     * 空间复杂度：O(mn)
     *
     * 利用分治(递归)将问题拆分为 f(i ,j) = f(i + 1, j) + f(i, j - 1)
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        // 加入缓存
        return helper(obstacleGrid, 0, 0, new HashMap<>());
    }

    public static int helper(int[][] obstacleGrid, int row, int col, Map<String, Integer> memo) {
        // 记录key
        String key = row + "-" + col;
        int result = 0;
        // 缓存中包含则直接返回
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 遇到障碍物则返回0
        if (obstacleGrid[row][col] == 1) {
            result = 0;
            memo.put(key, result);
            return result;
        }
        // 到达终点
        if (row == obstacleGrid.length - 1 && col == obstacleGrid[0].length - 1) {
            // 终点为障碍物则返回0，否则为1
            result = obstacleGrid[row][col] == 1 ? 0 : 1;
            memo.put(key, result);
            return result;
        }
        // 到达最后一列或最后一行，视情况继续往下走
        if (row == obstacleGrid.length - 1 || col == obstacleGrid[0].length - 1) {
            result = row == obstacleGrid.length - 1 ? helper(obstacleGrid, row, col + 1, memo) :
                    helper(obstacleGrid, row + 1, col, memo);
            memo.put(key, result);
            return result;
        }
        // 中间位置则直接调用分治
        result = helper(obstacleGrid, row, col + 1, memo) + helper(obstacleGrid, row + 1, col, memo);
        // 缓存结果值
        memo.put(key, result);
        return result;
    }
}
