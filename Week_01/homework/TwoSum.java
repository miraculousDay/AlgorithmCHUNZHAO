package homework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/two-sum/
 *
 * 解题思路：
 * 1.暴力破解，双for循环求解
 * @see #twoSum_1(int[], int)
 * 2.使用hash
 * @see #twoSum_2(int[], int)
 *
 * @author : tanyu
 * create at:  2021-01-23  10:09
 * @description: 两数之和
 */
public class TwoSum {

    /**
     * 测试数据
     */
    private static int[] testData = new int[]{2,7,11,15};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum_1(testData, 9)));
    }

    /**
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (hash.containsKey(target - nums[i])) {
                return new int[]{hash.get(target - nums[i]), i};
            }
            hash.put(nums[i], i);
        }
        return new int[]{};
    }
}