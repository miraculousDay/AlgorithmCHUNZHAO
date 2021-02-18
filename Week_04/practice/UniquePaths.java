package practice;

import java.util.Arrays;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * 解题思路
 * 1.动态规划
 * 由于我们每一步只能从向下或者向右移动一步，因此要想走到 (i, j)(i,j)，
 * 如果向下走一步，那么会从 (i-1, j)(i−1,j) 走过来；如果向右走一步，
 * 那么会从 (i, j-1)(i,j−1) 走过来。因此我们可以写出动态规划转移方程：
 * f(i, j) = f(i-1, j) + f(i, j-1)
 * @see #uniquePaths_1(int, int)
 *
 * 2.动态规划II，空间复杂度为O(n)，每次按照列来走
 *  @see #uniquePaths_2(int, int)
 *
 * @author : tanyu
 * create at:  2021-02-18  21:08
 * @description: 不同路径
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths_1(3, 3));
    }


    /**
     * 时间复杂度：O(min(m, n))
     * 空间复杂度：O(1)
     *
     * 参考：
     * https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
     *
     * 类似于：杨辉三角形
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_3(int m, int n) {
        //只跟第几行第几列有关，从m+n-2步中抽出m-1步
        long ans = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
            ans *= m + n - 2 - i;
            ans /= i + 1;
        }
        return (int) ans;
    }

    /**
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(n)
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths_2(int m, int n) {
        // 创建第一列
        int[] col = new int[n];
        // 赋值第一列
        Arrays.fill(col, 1);
        // 第二行开始进行遍历
        for (int i = 1; i < m; i++) {
            // 从每列第二个走到列的最后
            for (int j = 1; j < n; j++) {
                col[j] += col[j - 1];
            }
        }
        return col[n - 1];
    }

    /**
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     *
     * @param m 行数
     * @param n 列数
     * @return
     */
    public static int uniquePaths_1(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 1][n - 1];
    }
}