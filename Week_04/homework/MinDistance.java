package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * 解题思路
 * 1.动态规划。
 * s1 = "horse", s2 = "ros"
 * A.最近重复子问题：
 *   两个字符串的问题一般会转化为二维数组来进行思考，本题通过最后一个字符进行反推
 *   情况1：当s1与s2最后一个字符相等的时候，则无需编辑，可以转化为求s1(0, s1.length - 1)到s2(0, s2.length - 1)的最小编辑距离问题
 *   情况2：在情况1不成立的情况下，则s1可采取三个操作，分别如下：
 *         I：插入一个字符，在s1尾部插入s2的最后一个字符，则将变成求 s1(0, s1.length)到s2(0, s2.length - 1)的最小编辑距离
 *         II：删除一个字符：删除s1尾部的最有一个字符，则变成求 s1(0, s1.length - 1)到s2(0, s2.length)的最小编辑距离
 *         III：替换一个字符，替换s1最后一个字符为s2最后一个字符，则变成求 s1(0, s1.length - 1)到s2(0, s2.length - 1)的最小编辑距离
 *         以上三种操作情况完成后都需增加一次编辑次数
 *
 * B。状态数组：int[][] 内容存当前位置的最小编辑距离
 * C。dp方程式：
 *      当两个相等：f(i, j) = f(i - 1, j - 1)
 *      不相等：f(i, j) = min(f(i - 1, j), f(i, j - 1), f(i - 1, j - 1)) + 1
 *      进行编辑的时候，取三种情况中的最小值加一
 *
 * @see #minDistance_1(String, String)
 *
 * @author : tanyu
 * create at:  2021-02-22  22:13
 * @description: 编辑距离
 */
public class MinDistance {

    /**
     * 时间复杂度：O(mn) m为word1长度，n为word2长度
     * 空间复杂度：O(mn) m为word1长度，n为word2长度
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance_1(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 处理word2为空的情况
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        // 处理word1为空的情况
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        // 从1开始，0的情况已经在前面进行了处理
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // 判断字符是否相等，相等这比较子问题
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 不等则执行编辑操作
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}