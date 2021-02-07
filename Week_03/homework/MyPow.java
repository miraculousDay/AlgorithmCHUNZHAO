package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/powx-n/
 *
 * 解题思路：
 * 1.分治(递归，简洁版)
 * @see #myPow_1(double, int)
 *
 * @author : tanyu
 * create at:  2021-02-06  20:14
 * @description: Pow(x, n)
 */
public class MyPow {

    public static void main(String[] args) {
        System.out.println(myPow_1(2, 2));
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow_1(double x, int n) {
        // 退出条件
        if (n == 0) {
            return 1.0;
        }
        // 处理当n小于0的情况
        n = n < 0 ? -n : n;
        x = n < 0 ? 1 / x : x;
        // 分治处理递归
        return n % 2 == 0 ? myPow_1(x * x, n / 2) : x * myPow_1(x * x, n / 2);
    }

}