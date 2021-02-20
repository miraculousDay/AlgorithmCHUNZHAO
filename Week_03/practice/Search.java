package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * 解题思路
 * 1.二分查找，本题目中符合二分查找的三个标准
 * A.数组内容存在上下界，数组内容的有限
 * B.单调性，虽然数组进行了反转，但是反转之后的两部分都是单调递增的
 * C.数组可通过下标获取值
 * @see #search_1(int[], int)
 *
 * 2.极简二分法
 * @see #search_2(int[], int)
 *
 * @author : tanyu
 * create at:  2021-02-18 14:12
 * @description: 搜索旋转排序数组
 */
public class Search {


    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * 暂未理解 TODO
     *
     * @param nums
     * @param target
     * @return
     */
    public int search_2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid]))
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo == hi && nums[lo] == target ? lo : -1;
    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search_1(int[] nums, int target) {
        // 异常参数判断
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 1;
        while (left <= right) {
            // 防止下标越界
            mid = left + (right - left) / 2;
            // 先判断下是否找到目标值
            if (nums[mid] == target) {
                return mid;
            }
            // 判断是不是前半段有序
            if (nums[left] <= nums[mid]) {
                // 确定值是否在前半段
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 确定是否在后半段
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
