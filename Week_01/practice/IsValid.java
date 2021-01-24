package practice;

import java.util.Stack;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 1.用stack，利用先进后出的特性
 * @see #isValid(String)
 *
 * @author : tanyu
 * create at:  2021-01-23  18:13
 * @description: 有效的括号
 */
public class IsValid {

    private static String testData = "()[]{}";

    public static void main(String[] args) {

        System.out.println(isValid(testData));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        //当为空或者长度为奇数时，直接返回false
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            //增加相反的括号入栈
            if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
                //当出现对称括号时候，则从栈末尾取，当与栈中元素不等时，则返回false。
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        //如果栈为空，则表示括号都是匹配的
        return stack.isEmpty();
    }
}