package homework;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/subsets/
 *
 * 解题思路
 * 1。递归
 * @see #subsets_1(int[])
 *
 * @author : tanyu
 * create at:  2021-02-06  22:26
 * @description: 子集
 */
public class Subsets {

    private static int[] testData = new int[]{1, 2, 3};

    public static void main(String[] args) {
        subsets_1(testData);
    }

    /**
     * 时间复杂度：O(2n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        dfs(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }

    private static void dfs(List<List<Integer>> result,
                            ArrayList<Integer> temp,
                            int[] nums,
                            int level) {
        // 终止条件
        if (nums.length == level) {
            // 此处需要new ArrayList，temp是需要带入下层的变量，防止被其他层篡改
            result.add(new ArrayList<>(temp));
            return;
        }
        // 处理不添加的元素的情况
        dfs(result, temp, nums, level + 1);
        // 添加层级的元素
        temp.add(nums[level]);
        dfs(result, temp, nums, level + 1);
        // temp是带入下一层的可变的变量，需要清理当前层次增加的内容
        temp.remove(temp.size() - 1);
    }
}