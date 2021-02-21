package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * 解题思路
 * 1.动态规划
 * A。最近子问题为：f(i) = f(i - 1) + f(i - 2) 当前字符等价于前一个字符和前两个字符的组成情况
 * B。状态数组 int[]
 * C。dp方程：f(i) = f(i - 1) + f(i - 2)
 * @see #numDecodings_1(String)
 *
 * @author : tanyu
 * create at:  2021-02-21  20:58
 * @description: 解码方法
 */
public class NumDecodings {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public int numDecodings_1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        // 预先设置第一个值，用与i=2时候处理值
        dp[0] = 1;
        // 判断第一个字符是不是0，
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            // 当前一个字符在1-9之间，则数字dp[i] = dp[i] + dp[i-1]
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            // 前两个字符为10-26之间，则dp[i] += dp[i - 2]
            if (second >= 10 && first <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}