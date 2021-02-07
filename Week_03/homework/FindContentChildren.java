package homework;

import java.util.Arrays;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/assign-cookies/description/
 *
 * 解题思路
 * 1.贪心算法
 * @see #findContentChildren_1(int[], int[])
 *
 * @author : tanyu
 * create at:  2021-02-07  22:11
 * @description: 分发饼干
 */
public class FindContentChildren {

    /**
     * 时间复杂度：O(mlogn + nlogn) 其中 m 和 n 分别是数组 gArr 和 sArr 的长度
     * mlogn是排序数组需要的时间
     *
     * 空间负责度：O(mlogn + nlogn)
     *
     * @param gArr 胃口值
     * @param sArr 饼干大小
     * @return
     */
    public int findContentChildren_1(int[] gArr, int[] sArr) {
        int result = 0;
        if (gArr == null || gArr.length == 0
                || sArr == null || sArr.length == 0) {
            return result;
        }
        // 针对数组进行排序
        Arrays.sort(gArr);
        Arrays.sort(sArr);
        int childss = gArr.length;
        int sizes = sArr.length;
        // 净循坏处理
        for (int c = 0, s = 0; c < childss && s < sizes; c++, s++) {
            // 先按照最小的胃口进行匹配，然后依次匹配(此处体现贪心)
            while (s < sizes && sArr[s] < gArr[c]) {
                s++;
            }
            // 小于则表示满足胃口
            if (s < sizes) {
                result++;
            }
        }
        return result;
    }
}