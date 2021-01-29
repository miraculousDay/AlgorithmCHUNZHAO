package homework;

import java.lang.reflect.Array;
import java.util.*;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * 解题思路：
 * 1.堆排序， 使用map缓存数组出现的次数，然后就按照出现次数放入小顶堆中
 * @see #topKFrequent_1(int[], int)
 *
 * 2.桶排序法，使用map缓存数组出现的次数，使用数组下标表示出现的次数，下标的值存list保存数组中的值。
 * @see #topKFrequent_2(int[], int)
 *
 * @author : tanyu
 * create at:  2021-01-29 15:50
 * @description: 前 K 个高频元素
 */
public class TopKFrequent {

    private static int[] testData = new int[]{1, 2};

    public static void main(String[] args) {
        TopKFrequent d = new TopKFrequent();
        System.out.println(Arrays.toString(d.topKFrequent_1(testData, 2)));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent_2(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || k == 0) {
            return result;
        }
        // 将出现次数记录
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 使用List数组，数组下标为出现的次数，内容为出现次数的数字,0次是不可能出现的，所有长度加yi
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            bucket[value] = bucket[value] == null ? new ArrayList<>() : bucket[value];
            bucket[value].add(key);
        }
        // 从大到小处理返回值
        for (int i = bucket.length - 1; i >=0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(klogn)
     * 空间复杂度：O(n) 最欢的情况下，每个元素都不同
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent_1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 创建小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((x ,y) -> {
            return map.get(x) - map.get(y);
        });
        // 数据入堆
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (map.get(key) > map.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }
        // 创建结果数组，由于是小顶堆，因此需要从k-1开始赋值
        int[] result = new int[k];
        while (!queue.isEmpty()) {
            result[--k] = queue.poll();
        }
        return result;
    }
}
