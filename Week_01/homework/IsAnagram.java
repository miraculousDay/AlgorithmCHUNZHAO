package homework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode连接：
 * https://leetcode-cn.com/problems/valid-anagram/description/
 *
 * 字母异位词是指：两个字符串中每种字符个数是一样的，顺序可以不一样。
 *
 * 解题思路：
 * 1.使用Java线程的api，获取字符串的char[]，对char[]进行排序，比较数组即可
 * @see #isAnagram_1(String, String)
 *
 * 2.使用hash，保存第一个字符串中出现过字符的次数，然后在第二个字符串比对，存在某个字符则将值减一
 * @see #isAnagram_2(String, String)
 *
 * @author : tanyu
 * create at:  2021-01-24  19:21
 * @description: 有效的字母异位词
 */
public class IsAnagram {


    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram_1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        //对char[]进行排序，
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        //然后直接比较两个char数组
        return Arrays.equals(sArr, tArr);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(S) 其中S为字符集大小，此处 S=26。
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram_2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<>();
        //将s的字符加入map
        for (char c : s.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        //在map中减掉对应的字符，如果小于0，则返回false
        for (char c : t.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) - 1);
            if (table.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}