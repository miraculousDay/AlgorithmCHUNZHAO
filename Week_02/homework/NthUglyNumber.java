package homework;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/chou-shu-lcof/
 *
 * 解题思路：
 * 1.迭代，所有的丑数都包含2、3、5其中一个子因子，则将包含单个因子(2、3、5)的丑数算出来，
 * 然后按照大小放入数组，因此需要下个指针p2,p3,p5，用于乘以2、3、5。
 * @see #nthUglyNumber(int)
 *
 * @author : tanyu
 * create at:  2021-01-29 15:50
 * @description: 丑数
 */
public class NthUglyNumber {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] result = new int[n];
        // 三个指针，表示2、3、5
        int p2 = 0, p3 = 0, p5 = 0;
        // 初始化值
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            // 计算2、3、5对应的丑数
            int n2 = result[p2] * 2, n3 = result[p3] * 3, n5 = result[p5] * 5;
            // 比较三个数的大小，然后放入数组
            result[i] = Math.min(n2,Math.min(n3, n5));
            // 判断哪个指针需要自增
            if (result[i] == n2) {
                p2++;
            }
            if (result[i] == n3) {
                p3++;
            }
            if (result[i] == n5) {
                p5++;
            }
        }
        return result[n - 1];
    }
}
