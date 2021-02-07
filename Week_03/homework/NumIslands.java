package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * 解题思路
 * 1.深度优先遍历
 * @see #numIslands_1(char[][])
 *
 *
 * @author : tanyu
 * create at:  2021-02-07  19:05
 * @description: 岛屿数量
 */
public class NumIslands {

    /**
     * 时间复杂度：O(mn) 其中nn分别为行数和列数。
     * 空间复杂度：O(mn) 在最坏情况下，整个网格均为陆地，
     *
     * @param grid
     * @return
     */
    public int numIslands_1(char[][] grid) {
        int ans = 0;
        if (grid == null || grid.length == 0) {
            return ans;
        }
        // 行数
        int rowCount = grid.length;
        // 列数
        int colCount = grid[0].length;
        // 循环
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                // 当遇到一个陆地的时候，则采用深度优先遍历，判断该元素上下左右是否为陆地
                if (grid[i][j] == '1') {
                    ans++;
                    // 深度优先遍历
                    dfsMarking(i, rowCount, j, colCount, grid);
                }
            }
        }
        return ans;
    }

    private void dfsMarking(int i, int rowCount, int j, int colCount, char[][] grid) {
        // 终止条件，当超过length或小于0或者遇到0，则退出循环
        if (i < 0 || j < 0 || i >= rowCount || j >= colCount || grid[i][j] == '0') {
            return;
        }
        // 将该陆地置为0
        grid[i][j] = '0';
        // 深度优先遍历陆地上下左右的元素
        dfsMarking(i + 1, rowCount, j, colCount, grid);
        dfsMarking(i - 1, rowCount, j, colCount, grid);
        dfsMarking(i, rowCount, j - 1, colCount, grid);
        dfsMarking(i, rowCount, j + 1 , colCount, grid);
    }

}