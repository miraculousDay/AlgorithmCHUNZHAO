package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * 解题思路：
 * 1.递归
 * @see #generateParenthesis_1(int)
 *
 * @author : tanyu
 * create at:  2021-02-04  19:20
 * @description: 括号生成
 */
public class GenerateParenthesis {
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(generateParenthesis_1(3));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis_1(int n) {
        generate(0, 0, n, "");
        return result;
    }

    private static void generate(int rightLevel, int leftLevel, int n, String s) {
        if (rightLevel >= n && leftLevel >= n) {
            result.add(s);
            return;
        }
        // 1.左括号可以随时加，左括号数量要小于n
        if (leftLevel < n) {
            generate(rightLevel, leftLevel + 1, n, s + "(");
        }
        // 2.右括号需要加在左括号之后，也就是右括号增加的条件是：右括号个数必须少于左括号
        if (rightLevel < leftLevel) {
            generate(rightLevel + 1, leftLevel, n, s + ")");
        }
    }


}