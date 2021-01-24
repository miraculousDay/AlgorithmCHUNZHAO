package practice;

/**
 * LeetCode链接
 * https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
 *
 * 解题思路：
 * 1.暴力枚举
 *
 * 2.左右边界向中间收敛，使用双指针，一个从头部开始遍历，一个从尾部开始遍历。中间相遇则停止。
 * @see #maxArea_1(int[])
 *
 * @author : tanyu
 * create at:  2021-01-21  21:27
 * @description: 盛水最多的容器
 */
public class MaxArea {
    /**
     * 测试数据
     */
    private static int[] testData = new int[]{1,8,6,2,5,4,8,3,7};


    public static void main(String[] args) {
        System.out.println(maxArea_1(testData));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param height
     * @return
     */
    public static int maxArea_1(int[] height) {
        if (height == null || height.length < 0) {
            return -1;
        }
        //最大容量
        int maxArea = 0;
        //头部指针
        int j = 0;
        //尾部指针
        int i = height.length - 1;
        // 停止条件
        while (j < i) {
            // 获取最低点高度
            int min = Math.min(height[i], height[j]);
            // 计算面积，并保留最大面积计算的值
            maxArea = Math.max(min*(i-j),maxArea);
            //移动条件，高度低的往高度高的方向移动。
            if (height[i] >= height[j]) {
                j++;
            } else {
                i--;
            }
        }
        return maxArea;
    }
}
