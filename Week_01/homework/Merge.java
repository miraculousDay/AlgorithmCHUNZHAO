package homework;

import java.util.Arrays;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * 解题思路：
 * 1.api 利用Java现成api，进行数组赋值和排序
 * * @see #merge_1(int[], int, int[], int)
 * 2.正向双指针。从数组头部遍历
 * @see #merge_2(int[], int, int[], int)
 * 2.反向向双指针。从数组尾部遍历
 * @see #merge_3(int[], int, int[], int)
 *
 * @author : tanyu
 * create at:  2021-01-26 10:18
 * @description: 合并两个有序数组
 */
public class Merge {


    /**
     * 时间复杂度：O((n + m )log(m + n))
     * 空间复杂度：O(1)
     *
     * @param nums1
     * @param m     m的长度等于两个数组长度和
     * @param nums2
     * @param n
     */
    public void merge_1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     *
     * @param nums1
     * @param m     m的长度等于两个数组长度和
     * @param nums2
     * @param n
     */
    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        //先创建nums1的副本
        int[] nums1_copy = new int[m];
        //数组拷贝
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        // nums1_copy的指针
        int p1 = 0;
        // nums2的指针
        int p2 = 0;
        // nums1的下标，nums1作为用来遍历
        int p = 0;

        // 两个指针循环往下走，比较两个数组中的值的大小，然后放入nums1中
        while (p1 < m && p2 < n) {
            nums1[p++] = nums1_copy[p1] > nums2[p2] ? nums2[p2++] : nums1_copy[p1++];
        }
        // 如果nums1_copy长度比nums2长，则继续将nums1_copy剩下的拷贝到nums1中
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        } else if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    /**
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     *
     * @param nums1
     * @param m     m的长度等于两个数组长度和
     * @param nums2
     * @param n
     */
    public void merge_3(int[] nums1, int m, int[] nums2, int n) {
        // nums1的指针，从尾部遍历
        int p1 = m - 1;
        // nums2的指针，从尾部遍历
        int p2 = n - 1;
        // 总长度，也需要从尾部开始赋值
        int p = m + n - 1;
        // 两数组尾部比大，大的加入nums1尾部
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        // 当nums2中存在小于nums1的元素，则数组拷贝一次，没有的p2+1为0，则无元素进行拷贝
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

}
