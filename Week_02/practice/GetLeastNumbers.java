package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 *
 * 解题思路：
 * 1.java api解决
 * @see #getLeastNumbers_1(int[], int)
 *
 * 2.大顶堆来处里
 * @see #getLeastNumbers_2(int[], int)
 *
 * 3.快速排序
 * @see #getLeastNumbers_3(int[], int)
 *
 * @author : tanyu
 * create at:  2021-01-28 16:18
 * @description: 最小的k个数
 */
public class GetLeastNumbers {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(logn)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_3(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }

    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    /**
     * 时间复杂度：O(nlogk) logn是排序的复杂度
     * 空间复杂度：O(k)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_2(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        // 使用一个最大堆（大顶堆）
        // Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
        Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
        for (int e : arr) {
            // 当前数字小于堆顶元素才会入堆
            if (heap.size() < k) {
                heap.offer(e);
                // 小于则入队列
            } else if (e < heap.peek()) {
                heap.poll();
                heap.offer(e);
            }
        }
        // 将堆中的元素存入数组
        return heap.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 时间复杂度：O(nlogn) logn是排序的复杂度
     * 空间复杂度：O(logn)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_1(int[] arr, int k) {
        int[] result = new int[k];
        if (arr == null || arr.length == 0) {
            return result;
        }
        if (arr.length <= k) {
            return arr;
        }
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}
