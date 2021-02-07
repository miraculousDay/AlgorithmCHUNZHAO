package homework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 * 解题思路：
 * 1.hash处理
 * @see #majorityElement_1(int[])
 *
 * 2.排序
 * @see #majorityElement_2(int[])
 *
 * 3.摩尔投票
 * @see #majorityElement_3(int[])
 *
 * 4.分治
 * @see #majorityElement_4(int[])
 *
 * @author : tanyu
 * create at:  2021-02-06  14:29
 * @description: 多数元素
 */
public class MajorityElement {

    private static int[] testData = new int[]{3,2,3};

    public static void main(String[] args) {
        System.out.println(majorityElement_4(testData));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int majorityElement_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     *
     * 排序之后直接取中间位置的元素
     *
     * @param nums
     * @return
     */
    public int majorityElement_2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * <p>
     * 把众数当做1，其他的数字当做-1，众数的规则是该数字出现的次数大于数组长度/2，
     *
     * @param nums
     * @return
     */
    public int majorityElement_3(int[] nums) {
        Integer votingNum = null;
        int count = 0;
        for (int num : nums) {
            //众数的赋值，当count为0的时候，则表示众数需要重置。
            if (count == 0) {
                votingNum = num;
            }
            count += votingNum == num ? 1 : -1;
        }
        return votingNum;
    }

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     *
     * 利用分治的思想，当一个数是众数的时候，将数组分成两组，则众数至少在一个分组是众数
     *
     * @param nums
     * @return
     */
    public static int majorityElement_4(int[] nums) {
        return majorityElementResc(nums, 0, nums.length - 1);
    }

    private static int majorityElementResc(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }
        // 获取中间位置
        int mid = (hi - lo) / 2 + lo;
        // 获取左右数组的众数
        int left = majorityElementResc(nums, lo, mid);
        int right = majorityElementResc(nums, mid + 1, hi);
        // 如果众数一样，则表示这个数为整个数组的众数
        if (left == right) {
            return left;
        }
        // 如果左右数组的众数不一致，则需要重新啊统计这个数在数组中出现的次数
        int leftCount = countNum(nums, left, lo, hi);
        int rightCount = countNum(nums, right, lo, hi);
        // 通过次数判断那个数字是众数
        return leftCount > rightCount ? left : right;
    }

    private static int countNum(int[] nums, int num, int lo, int hi) {
        // 统计对应数字出现的次数
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

}