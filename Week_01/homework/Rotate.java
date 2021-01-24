package homework;

import java.util.Arrays;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * 解题思路：
 * 1.增加一个临时的数组保存源数组，然后根据位移后的下标在临时数组中放入元素
 *
 * @author : tanyu
 * create at:  2021-01-24  14:33
 * @description: 旋转数组
 * @see #rotate_1(int[], int)
 * <p>
 * 2.先整体反转，然后将0 - k-1 元素再反转，然后将k - nums.length反转。
 * 详细解析
 * @see #rotate_2(int[], int)
 */
public class Rotate {

    /**
     * 测试数据
     */
    private static int[] testData = new int[]{1, -2};

    public static void main(String[] args) {
        rotate_2(testData, 3);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     */
    public static void rotate_1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        // 新数组
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            //找到源数组中元素位移后的下标
            temp[(i + k) % n] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, n);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     */
    public static void rotate_2(int[] nums, int k) {
        k %= nums.length;
        if (nums == null || nums.length == 0) {
            return;
        }
        //相当于右移了nums.length
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void reverse(int[] source, int begin, int end) {
        if (source == null || source.length == 0) {
            return;
        }
        if (source.length <= end) {
            end = source.length - 1;
        }
        while (begin < end) {
            int temp = source[begin];
            source[begin++] = source[end];
            source[end--] = temp;
        }
    }
//
//    原始数组	1 2 3 4 5 6 7
//    翻转所有元素	7 6 5 4 3 2 1
//    翻转 [0, k\bmod n - 1][0,kmodn−1] 区间的元素	5 6 7 4 3 2 1
//    翻转 [k\bmod n, n - 1][kmodn,n−1] 区间的元素   5 6 7 1 2 3 4
}