package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * 解题思路
 * 1.二分查找，此题满足二分查找标准
 * A.该问题有上下界，一个数的平方根在1-x之间
 * B.该问题是单调递增的，一个数的平方根在1-x之间
 * C.该问题可通过下标获取值。一个数的平方根在1-x之间
 * @see #isPerfectSquare_1(int)
 *
 * 2.牛顿迭代
 * @see #isPerfectSquare_2(int)
 *
 * @author : tanyu
 * create at:  2021-02-18 11:48
 * @description: 有效的完全平方数
 */
public class IsPerfectSquare {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare_1(2147483647));
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare_2(int num) {
        // 牛顿迭代
        if (num == 1) {
            return true;
        }
        long a = num;
        while (a * a > num) {
            a = (a + num / a) / 2;
        }
        return a * a == num;
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare_1(int num) {
        if (num == 1) {
            return true;
        }
        if (num <= 3) {
            return false;
        }
        // 此处使用long是防止数据精准度过高时候，导致超时
        long left = 1;
        long right = num;
        long mid = 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid * mid > num) {
                right = mid - 1;
            } else if (mid * mid < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
