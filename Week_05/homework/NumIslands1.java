package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * 解题思路
 * 1.并查集解决岛屿问题
 * @see #numIslands_1(char[][])
 *
 * @author : tanyu
 * create at:  2021-02-24  20:58
 * @description: 岛屿数量
 */
public class NumIslands1 {
    /**
     * 时间复杂度：O(m*n*2)
     * 空间复杂度：O(m*n)
     *
     * @param grid
     * @return
     */
    public int numIslands_1(char[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        int[][] distance = new int[][]{{1, 0},{0, 1}};
        NumIslands_Union union = new NumIslands_Union(grid);
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    for (int[] arr : distance) {
                        int x = i + arr[0];
                        int y = j + arr[1];
                        if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == '1') {
                            union.union(i * col + j, x * col + y);
                        }
                    }
                }
            }
        }
        return union.getCount();
    }

    /**
     * 岛屿并查集
     */
    static class NumIslands_Union {
        private int count;
        private int[] parent;

        /**
         * 传入岛屿数组
         *
         * @param grid
         */
        public NumIslands_Union(char[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            parent = new int[row * col];
            // 挨个遍历，当前为1的时候，则设置值，count++
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = i * col + j;
                        parent[index] = index;
                        count++;
                    }
                }
            }
        }

        /**
         * 查找值
         *
         * @param index
         * @return
         */
        public int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return index;
        }

        /**
         * 合并值
         *
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp != rootq) {
                parent[rootp] = rootq;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
