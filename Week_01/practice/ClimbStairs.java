package practice;

/**
 * LeetCode链接
 * https://leetcode-cn.com/problems/climbing-stairs/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * 解题思路：
 * 1.尝试使用暴力枚举(本题不可行)
 * 2.找最近重复子问题,递归方式，预定义f1(第一阶),f2(第二阶),f3(第三阶)，然后通过三者相互交换求出n阶的爬楼梯方法。
 * @see #climbStairs_1(int)
 *
 * @author : tanyu
 * create at:  2021-01-21  23:11
 * @description: 爬楼梯
 */
public class ClimbStairs {


    public static void main(String[] args) {
        System.out.println(climbStairs_1(4));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n 楼梯层数
     * @return
     */
    public static int climbStairs_1(int n) {
        if (n < 4) {
            return n;
        }
        //初始化前三步的值
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        for (int i = 3; i <= n; i++) {
            //向后移动
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }
}