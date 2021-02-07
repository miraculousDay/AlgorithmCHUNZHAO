package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/jump-game/
 *
 * 解题思路
 * 1.贪心算法，从头部开始
 * @see #canJump_1(int[])
 *
 * 2.贪心算法，从尾部开始
 * @see #canJump_2(int[])
 *
 * @author : tanyu
 * create at:  2021-02-07  22:42
 * @description: 跳跃游戏
 */
public class CanJump {
    private static int[] testData = new int[]{2,3,1,1,4};

    public static void main(String[] args) {
        canJump_1(testData);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static boolean canJump_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // 最后到达的位置是最后一个下标，然后从最后反推，推断可到达最后的下标值
        int endReachable = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= endReachable) {
                endReachable = i;
            }
        }
        // 最后判断是否是从头部开始的
        return endReachable == 0;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static boolean canJump_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // 可移动的步数
        int canMove = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当下标在可移动的步数之内
            if (i <= canMove) {
                // 比较当前元素与之前可移动的步数，i + nums[i]加i是因为需要从i的下标开始移动
                canMove = Math.max(canMove, i + nums[i]);
                // 当可移动的步数大于等于最后一个下标，则返回true
                if (canMove >= nums.length -1) {
                    return true;
                }
            }
        }
        return false;
    }
}