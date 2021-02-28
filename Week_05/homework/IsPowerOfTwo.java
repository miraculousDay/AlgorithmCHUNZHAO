package homework;

/**
 * LeetCode链接
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * 解题思路
 * 1.位运算
 * @see #isPowerOfTwo_1(int)
 *
 * @author : tanyu
 * create at:  2021-02-28  22:54
 * @description:  2的幂
 */
public class IsPowerOfTwo {
    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo_1(int n) {
        if (n == 0) {
            return false;
        }
        // 防止测试数据精度过高
        long x = n;
        return (x & (x - 1)) == 0;
    }
}