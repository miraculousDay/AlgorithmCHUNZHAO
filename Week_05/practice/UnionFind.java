package practice;

/**
 * 并查集实例
 *
 * @author : tanyu
 * create at:  2021-02-24 19:54
 * @description: 并查集demo
 */
public class UnionFind {
    private int count;
    private int[] parent;

    public UnionFind(int n) {
        this.count = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        this.parent[rootP] = rootQ;
        this.count--;
    }

    public static void main(String[] args) {
        UnionFind union = new UnionFind(10);
        union.union(1,9);
        union.union(9,2);
        System.out.println(union.find(1));
        System.out.println(union.find(9));
        System.out.println(union);
        // 1 1 0
        // 1 1 0
        // 0 0 1
        int[][] test = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(test));;
    }


    /**
     * LeetCode链接
     * https://leetcode.com/problems/number-of-provinces/
     *
     * @param grid
     * @return
     */
    public static int findCircleNum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        UnionFind union = new UnionFind(row);
        for (int i = 0; i < row - 1; i++) {
            for (int j = i + 1; j < row; j++) {
                if (grid[i][j] == 1) {
                    union.union(i, j);
                }
            }
        }
        return union.count;
    }
}
