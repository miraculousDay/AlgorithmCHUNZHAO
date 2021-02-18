package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * 解题思路
 * 1.二分查找，当前问题符合二分查找的三个标准：
 * A.存在上下界，x的平方根一定在1-x之间
 * B.值是单调变化的，值再1-x之间
 * C.可通过下标获取值，值在1-x之间
 * @see #mySqrt_1(int)
 *
 * 2.牛顿迭代
 * https://www.zhihu.com/question/20690553
 *
 * @author : tanyu
 * create at:  2021-02-18 09:45
 * @description: x 的平方根
 */
public class MySqrt {

    public static void main(String[] args) {
        System.out.println(mySqrt_2(9));
    }

    /**
     * 时间复杂度：O(logx)
     * 空间复杂度：O(1)
     *
     * @param x
     * @return
     */
    public static int mySqrt_1(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        // 防止精准度高的测试数据，此处使用long
        long left = 1;
        long right = x;
        long mid = 1;
        while (left <= right) {
            // 此处处理防止下越界
            mid = left + (right - left) / 2;
            // 比较中间值的平方是否大于x
            if (mid * mid > x) {
                right = right - 1;
            } else {
                left = left + 1;
            }
        }
        return (int) right;
    }

    /**
     * 时间复杂度：O(logx)
     * 空间复杂度：O(1)
     *
     * 此方法效率优于二分查找
     * 此方法牛顿迭代方法模板为：x为输入的求解值
     * (a + x / a) / 2
     *
     * @param x
     * @return
     */
    public static int mySqrt_2(int x) {
        long a = x;
        while (a * a > x) {
            a = (a + x / a) / 2;
        }
        return (int) a;
    }
}
