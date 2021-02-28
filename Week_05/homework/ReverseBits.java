package homework;

/**
 * LeetCode链接
 * https://leetcode-cn.com/problems/reverse-bits/
 *
 * 解题思路
 * 1.位运算
 * @see #reverseBits_1(int)
 *
 * @author : tanyu
 * create at:  2021-02-28  23:01
 * @description: 颠倒二进制位
 */
public class ReverseBits {

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int reverseBits_1(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // 定位向前移动，n & 1 获取最低位的二进制值
            result = (result << 1) + (n & 1);
            n >>= 1;
        }
        return result;
    }
}