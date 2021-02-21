package practice;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * 解题思路
 * 1.动态规划，动态规划中两个字符串的问题一般都是转化为二维数组进行比较。
 * 此题中是计算两个字符串的公共子序列，通过以下演算可推算到dp方程式
 * A.s1为""，s2为""，则公共子序列为0
 * B.s1或s2为"",则公共子序列为0
 * C.当s1='a'，s2='abcde'，则可比较计算s1的字符是否在s2存在即可
 * D.当s1="abcde"，s2="ace"，第一个字符是相同，则起码有一个公共子序列，那么两个字符串的公共子序列问题则可以
 * 处理求"bcde"与"ce"的公共子序列 + 1，则dp方程式为：f(i, j) = f(i - 1, j - 1) + 1
 * E.当s1="bcde"，s2="ace"，当第一个不同的时候，则需要比较"bcde"与"ce"、"cde"与"ace"中的最大值，
 * 则dp方程式为：f(i, j) = Max(f(i - 1, j), f(i, j - 1))
 *
 * @see #longestCommonSubsequence_1(String, String)
 *
 * 2.动态规划2
 * @see #longestCommonSubsequence_2(String, String)
 *
 * 子序列：是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * @author : tanyu
 * create at:  2021-02-20  14:13
 * @description: 最长公共子序列
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        longestCommonSubsequence_2("a", "acae");
    }

    /**
     * 时间复杂度：O(mn) m为text1的长度，n为text2的长度
     * 空间复杂度：O(mn) m为text1的长度，n为text2的长度
     *
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence_1(String text1, String text2) {
        // 非法入参处理
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int row = text1.length();
        int col = text2.length();
        // 创建二维数组，此处+1是保证数组第一行和第一列为0，用于下面遍历使用
        int[][] arr = new int[row + 1][col + 1];
        // 从1开始遍历，0的位置以0为初始值
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                // 比较两个字符的值，利用解题思路中的dp方程式进行计算
                arr[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? arr[i -1][j - 1] + 1 :
                        Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
        return arr[row][col];
    }

    /**
     * 时间复杂度：O(mn) m为text1的长度，n为text2的长度
     * 空间复杂度：O(min(m, n)) m为text1的长度，n为text2的长度
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence_2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 获取char[]数组方便下面遍历
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[] dp = new int[n + 1];
        // 从1开始遍历，0的位置初始值为0
        for (int i = 1; i <= m; i++) {
            // 预设初始值，用于两个字符相等时候进行运算，因为每一行开始，都是从头开始。
            int pre = 0;
            for (int j = 1; j <= n; j++) {
                // 临时值，用于保存当前行的值
                int temp = dp[j];
                // 比较字符是否相等，相等则在加一，否则比较dp[j], dp[j - 1]中的最大值
                dp[j] = str1[i - 1] == str2[j - 1] ? pre + 1 : Math.max(dp[j], dp[j - 1]);
                // 重置pre的值，用于下个循坏的计算
                pre = temp;
            }
        }
        return dp[n];
    }
}
