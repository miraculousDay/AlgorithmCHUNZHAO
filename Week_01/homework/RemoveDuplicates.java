package homework;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 解题思路：
 * 1.双指针，左指针只存不同的元素，找到不同的元素则覆盖
 * @see #removeDuplicates_1(int[])
 *
 * 2.双指针，利用已排序的特定进行比较
 *
 * @author : tanyu
 * create at:  2021-01-24  13:49
 * @description: 删除数组重复元素
 */
public class RemoveDuplicates {
    private static int[] testData = new int[]{1, 1, 2, 2, 3, 3, 4, 4};

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates_1(int[] nums) {
        // 不重复元素的下标
        int i = 0;
        if (nums == null || nums.length == 0) {
            return i;
        }
        for (int j = 1; j < nums.length; j++) {
            //不重复则，直接覆盖值
            if (nums[j] == nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates_2(int[] nums) {
        int i = 0;
        if (nums == null || nums.length == 0) {
            return i;
        }
        for (int num : nums) {
            //当前元素与上一个元素进行比较，不同则负覆盖元素
            if (i == 0 || num > nums[i - 1]) {
                nums[i++] = num;
            }
        }
        return i;
    }
}