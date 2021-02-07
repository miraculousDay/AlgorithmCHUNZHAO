package homework;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * 解题思路
 * 1.深度优先遍历
 * @see #numIslands_1(char[][])
 *
 * 2.广度优先遍历
 * @see #numIslands_2(char[][])
 *
 * @author : tanyu
 * create at:  2021-02-07  19:05
 * @description: 岛屿数量
 */
public class NumIslands {

    /**
     * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * 空间复杂度：O(min(M,N))，
     * 在最坏情况下，整个网格均为陆地，队列的大小可以达到min(M,N)。
     *
     * @param grid
     * @return
     */
    public int numIslands_2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 获取行数
        int nr = grid.length;
        // 获取列数
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                // 找到岛屿
                if (grid[r][c] == '1') {
                    // 岛屿数量加一
                    ++num_islands;
                    grid[r][c] = '0';
                    // 队列处理上下左右元素的情况
                    Queue<Integer> neighbors = new LinkedList<>();
                    // 处理入队列的元素，方便获取row和col
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        // 获取行下标
                        int row = id / nc;
                        // 获取列下标
                        int col = id % nc;
                        // 处理上下左右元素的情况
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return num_islands;
    }


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
        dfsMarking(i, rowCount, j + 1, colCount, grid);
    }

}