package practice;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * 解题思路
 * 1.动态规划
 * A.最近子问题为：创建状态数组int[n + 1]，数组内容保存组成i下标需要的最少完全平方数，那么当i的最少完全平方根可以转化为：
 * f(i) = f(i - j * j) + 1;  这种请款下：i可由j*j这个数和f(i - j*j)这个数的最少完全平方根组成
 * B.状态数组为： int[n + 1]
 * C.dp方程为：f(i) = min(d[i], d[i - j*j] + 1)
 * @see #numSquares_1(int)
 *
 * @author : tanyu
 * create at:  2021-02-24  09:18
 * @description:  完全平方数
 */
public class NumSquares {

    public static void main(String[] args) {
        System.out.println(numSquares_1(12));
    }

    /**
     * 时间复杂度：O(n * sqrt(n)) sqrt平方根
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static int numSquares_1(int n) {
        // dp状态数组
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最坏的情况
            dp[i] = i;
            // 从1-i进行计算i的最少完全平方根
            for (int j = 1; i - j * j >= 0; j++) {
                // dp方程式
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
