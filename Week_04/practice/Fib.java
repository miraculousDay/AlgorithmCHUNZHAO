package practice;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 * 解题思路
 * 1.斐波那契数列公式直接计算(傻递归)
 * @see #fib_1(int)
 *
 * 2.记忆化加递归，在傻递归的基础上加上缓存
 * @see #fib_2(int, int[])
 *
 * 3.动态规划，此问题可通过第一位和第二位依次递推到最后的数字。
 * @see #fib_3(int)
 *
 * @author : tanyu
 * create at:  2021-02-07  16:41
 * @description: 斐波那契数列
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(fib(45));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int fib_3(int n) {
        int a0 = 0, a1 = 1, sum = 0;
        // 此处从0开始循环，则会求出n + 1的结果，所有return时返回a0
        for (int i = 0; i < n; i++) {
            sum = (a0 + a1) % 1000000007;
            a0 = a1;
            a1 = sum;
        }
        return a0;
    }


    public static int fib(int n) {
        int[] memo = new int[n + 1];
        return fib_2(n, memo);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static int fib_2(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        // 使用数组保存计算过的值
        if (memo[n] == 0) {
            memo[n] = fib_2(n - 1, memo) + fib_2(n - 2, memo);
        }
        return memo[n]%1000000007;
    }

    /**
     * 时间复杂度：O(2n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int fib_1(int n) {
        if (n <= 1) {
            return n;
        }
        return (fib_1(n - 1) + fib_1(n - 2)) % 1000000007;
    }
}
