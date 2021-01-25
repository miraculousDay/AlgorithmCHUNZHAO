package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/3sum/
 *
 * 解题思路：
 * 1.暴力求解，三层for循环
 *
 * 2.使用hash
 * @see #threeSum_2(int[])
 *
 * 3.双指针，左右夹逼方法
 * @see #threeSum_3(int[])
 *
 * @author : tanyu
 * create at:  2021-01-23  10:43
 * @description: 三数之和
 * @see #threeSum_1(int[])
 *

 */
public class ThreeSum {

    private static int[] testData = new int[]{-1,0,1,2,-1,-4};

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum_3(testData);
        lists.stream().forEach(System.out::println);
    }

    /**
     * 时间复杂度：O(n3)
     * 空间复杂度：O(?) TODO
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_1(int[] nums) {

        return null;
    }

    public List<List<Integer>> threeSum_2(int[] nums) {

        return null;
    }

    /**
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum_3(int[] nums) {
        //参数判空
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        //排序，时间复杂度 O(nlogn)
        Arrays.sort(nums);
        //nums.length - 2 双指针需要两个下标位置
        for (int k = 0; k < nums.length - 2; k++) {
            //如果当前数字大于0，则后面数字也想大于0.则没有循环的必要了
            if (nums[k] > 0) {
                break;
            }
            //nums[k] == nums[k - 1] 两个数字相同则跳过，执行下一层循环
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            //左指针，从当前元素+1开始
            int i = k + 1;
            //右指针，从尾部元素开始
            int j = nums.length - 1;
            //每次for循环，都执行双指针进行左右夹逼，当i==j则进行for的下一轮
            while (i < j) {
                //三数相加
                int sum = nums[k] + nums[i] + nums[j];
                //当和小于0，则表示左侧值过小，左指针需要向右移动
                if (sum < 0) {
                    //再次判断i < j，然后做++i的操作
                    while (i < j && nums[i] == nums[++i]) ;
                //当和大于0，则表示右侧值过大，右指针需要向左移动
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    //结果正好为0，记录元素值，此处无需担心重复值，for循坏中已经处理重复值问题
                    result.add(new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j])));
                    //左右指针都移动
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }
        return result;
    }

}