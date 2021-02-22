package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/palindromic-substrings/
 *
 * 解题思路
 * 1.动态规划
 * A.最近子问题为：
 *   判断(i, j)之间的字符串是否为回文串，则需要f(i) == f(j) && f(i + 1, j - 1)之间的字符串为回文字符串，本文中单独的a可作为回文串，则需要增加判断
 *   i与j之间的巨鹿关系，意味当 f(i) == f(j) && j - i < 2 的时候，也是回文串。
 * B.状态数组为： boolean[][]
 * C.dp方程为：f(i, j) = f(i) == f(j) && (j - i < 2 || f(i + 1, j - 1))  f(i + 1, j - 1)这个子字符串为回文串
 * @see #countSubstrings_1(String)
 *
 * @author : tanyu
 * create at:  2021-02-22  11:44
 * @description:  回文子串
 */
public class CountSubstrings {

    public int countSubstrings_1(String s) {
        // 异常参数处理
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        // dp状态
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[i - 1][j + 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;


//        // 动态规划法
//        boolean[][] dp = new boolean[s.length()][s.length()];
//        int ans = 0;
//
//        for (int j = 0; j < s.length(); j++) {
//            for (int i = 0; i <= j; i++) {
//                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
//                    dp[i][j] = true;
//                    ans++;
//                }
//            }
//        }
//
//        return ans;

    }
}
