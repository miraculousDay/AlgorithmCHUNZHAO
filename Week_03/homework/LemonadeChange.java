package homework;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/lemonade-change/solution/
 *
 * 解题思路：
 * 1.迭代中进行逻辑处理
 * @see #lemonadeChange_1(int[])
 *
 * @author : tanyu
 * create at:  2021-02-06  16:32
 * @description: 柠檬水找零
 */
public class LemonadeChange {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange_1(int[] bills) {
        int fiveCount = 0, tenCount = 0;
        for (int i : bills) {
            if (i == 5) {
                fiveCount++;
            } else if (i == 10) {
                fiveCount--;
                tenCount++;
                // 此处有点贪心算法的味道，当收取到20的，则优先从10扣掉
            } else if (tenCount > 0) {
                tenCount--;
                fiveCount--;
            } else {
                fiveCount -= 3;
            }
            if (fiveCount < 0) {
                return false;
            }
        }
        return true;
    }

}