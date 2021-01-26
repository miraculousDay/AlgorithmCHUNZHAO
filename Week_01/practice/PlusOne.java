package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/plus-one/solution/java-shu-xue-jie-ti-by-yhhzw/
 *
 * 解题思路：
 * 1.只有当所有的数组元素都是9的时候才考虑数组长度加一。
 * @see #plusOne(int[])
 * 
 * @author : tanyu
 * create at:  2021-01-26 10:18
 * @description: 加一
 */
public class PlusOne {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{};
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            // 只要存在一个下标对应的值不大于10，则直接返回数组
            if (digits[i] < 10) {
                return digits;
            }
            digits[i] = digits[i] % 10;
        }
        // 源数组所有的内容都是9
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
