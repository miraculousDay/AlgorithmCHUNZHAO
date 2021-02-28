package homework;

/**
 * LeetCode链接
 * https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * 解题思路
 * 1.循环和位移动
 * @see #hammingWeight_1(int)
 *
 * 2.位操作的小技巧
 * @see #hammingWeight_2(int)
 *
 * @author : tanyu
 * create at:  2021-02-28  22:34
 * @description: 位1的个数
 */
public class HammingWeight {

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int hammingWeight_1(int n) {
        // 结果
        int count = 0;
        // 0000 0001 通过移动末尾1进行&运算
        int mark = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mark) != 0) {
                count++;
            }
            mark <<= 1;
        }
        return count;
    }

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * 删除最低位的一个1
     * 例如：
     * n=3:
     * 0000 0011
     * n-1:
     * 0000 0010
     *
     * n & n - 1的结果
     * 0000 0010
     *
     * @param n
     * @return
     */
    public int hammingWeight_2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}