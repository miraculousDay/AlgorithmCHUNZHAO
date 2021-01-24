package homework;

import java.util.Arrays;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * 解题思路：
 *
 * 1.开辟另外一个同长度数组，对目标数组进行过滤，当数组中元素不为0则放入新数组中。
 * @see #moveZero_1(int[])
 *
 * 2.遍历目标数组，记录0的个数，并将非0元素放在前面，最后在数组尾部追加相应0的个数。
 * @see #moveZero_2(int[])
 *
 * 3.使用双指针方式，一个指针用于循环，另外一个指针用于记录非0位置，并在循环完成0与非0元素之前的交换
 * @see #moveZero_3(int[])
 *
 * @author : tanyu
 * create at:  2021-01-20  21:13
 * @description: 移动0
 */
public class MoveZeroes {

    /**
     * 测试数据
     */
    private static int[] testData = new int[]{0,0,0,0,0,1};


    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZero_3(testData)));
    }


    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param sourceArr
     * @return
     */
    private static int[] moveZero_3(int[] sourceArr) {
        //记录0的下标
        int j = 0;
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] != 0) {
                //存在为0未进行交换，则i和j的下标需要交换位置
                if (i != j) {
                    sourceArr[j] = sourceArr[i];
                    sourceArr[i] = 0;
                }
                j++;
            }
        }
        return sourceArr;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param sourceArr
     * @return
     */
    private static int[] moveZero_2(int[] sourceArr) {
        //记录非0的下标
        int j = 0;
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] != 0) {
                sourceArr[j++] = sourceArr[i];
            }
        }
        //补0
        for (; j < sourceArr.length; j++) {
            sourceArr[j] = 0;
        }
        return sourceArr;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param sourceArr 源数组
     */
    private static int[] moveZero_1(int[] sourceArr) {
        if (sourceArr == null || sourceArr.length == 0) {
            return new int[0];
        }
        int[] targetArr = new int[sourceArr.length];
        // 记录0的个数
        int zeroCount = 0;
        for (int i = 0; i < sourceArr.length; i++) {
            if (sourceArr[i] == 0) {
                zeroCount++;
            } else {
                // 赋值非0元素
                targetArr[i - zeroCount] = sourceArr[i];
            }
        }
        // 补充0
        for (int i = targetArr.length - zeroCount; i < targetArr.length; i++) {
            targetArr[i] = 0;
        }
        return targetArr;
    }
}