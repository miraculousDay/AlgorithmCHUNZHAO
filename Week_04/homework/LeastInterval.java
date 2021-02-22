package homework;

import java.util.Arrays;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/task-scheduler/
 *
 * 解题思路
 * 1.贪心算反
 * 首先安排出现最多的任务，然后后冷却时间内安排其他任务进行执行。
 * maxTimes为出现次数最多的那个任务出现的次数，maxCount为跟最多任务次数一样的任务数目
 * 计算公式为： (maxTimes - 1)*(n + 1) + maxCount
 *
 * LeetCode上的图文解答
 * https://leetcode-cn.com/problems/task-scheduler/solution/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9/
 * @see #leastInterval_1(char[], int)
 *
 * @author : tanyu
 * create at:  2021-02-21  22:06
 * @description: 任务调度器
 */
public class LeastInterval {

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval_1(char[] tasks, int n) {
        // 异常参数处理
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        // 任务出现的次数数组
        int[] buckets = new int[26];
        // 由于全是大写字母，则使用数组下标表示字母，值为任务出现的个数
        for (int i = 0; i < tasks.length; i++) {
            buckets[tasks[i] - 'A']++;
        }
        // 整体排序
        Arrays.sort(buckets);
        // 最多出现的任务次数
        int maxTimes = buckets[25];
        // 预先定义跟最多次数一样的任务的个数
        int maxCount = 1;
        for (int i = 25; i >= 1; i--) {
            // 跟最多任务次数比较，如果相等则加一
            if (buckets[i] == buckets[i -1]) {
                maxCount++;
            } else {
                break;
            }
        }
        // 计算公式会可能会出现偏下的结果，因为与tasks.lenght比较，取最大值
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);
    }
}