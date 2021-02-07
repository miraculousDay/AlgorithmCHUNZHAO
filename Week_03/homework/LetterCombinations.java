package homework;

import java.util.*;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 解题思路
 * 1.递归
 * @see #letterCombinations_1(String)
 *
 * 2.迭代(相当于使用队列)
 * @see #letterCombinations_2(String)
 *
 * @author : tanyu
 * create at:  2021-02-07  09:43
 * @description: 电话号码的字母组合
 */
public class LetterCombinations {

    public static void main(String[] args) {
        System.out.println(letterCombinations_2("23"));
    }


    /**
     * 时间复杂度：O(3m x 4n)
     * 空间复杂度：O(m)
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations_2(String digits) {
            LinkedList<String> result = new LinkedList<>();
        if (digits.length() == 0) {
            return result;
        }
        // hash缓存
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // 预先放入队列元素
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            // 获取缓存中的下标
            int index = Character.getNumericValue(digits.charAt(i));
            // 当元素与当前循环下标i一致的时候，则需要取出原先所有在队列中的值，然后用于下次结果的拼装。
            while (result.peek().length() == i) {
                String s = result.remove();
                for (char c : mapping[index].toCharArray()) {
                    result.add(s + c);
                }
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(3m x 4n) m表示输入字符串的长度 n表示数组中数字表示的字符长度
     * 空间复杂度：O(m + n) m表示输入字符串的长度 n表示数组中数字表示的字符长度
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations_1(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        // hash缓存键值对
        Map<Character, String> hash = new HashMap(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(result, hash, 0, "", digits);
        return result;
    }

    private static void backtrack(List<String> result,
                                  Map<Character, String> hash,
                                  int level,
                                  String sourceStr,
                                  String digits) {
        // 终止条件
        if (level == digits.length()) {
            result.add(sourceStr);
            return;
        }
        // 获取数字对应的字母
        String letter = hash.get(digits.charAt(level));
        // 按个对字母进行处理下一层的处理
        for (char c : letter.toCharArray()) {
            backtrack(result, hash, level + 1, sourceStr + c, digits);
        }
    }
}