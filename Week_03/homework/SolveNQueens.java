package homework;

import java.util.*;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/n-queens/
 *
 * 解题思路
 * 1。回溯
 * @see #solveNQueens_1(int)
 *
 * @author : tanyu
 * create at:  2021-02-07  11:21
 * @description: N皇后
 */
public class SolveNQueens {

    public static void main(String[] args) {
        System.out.println(solveNQueens_1(4));;
    }

    /**
     * 时间复杂度：O(n!)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens_1(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        int[] queens = new int[n];
        // 预先设置皇后攻击的位置，x轴的
        Set<Integer> columns = new HashSet<>();
        // 对角线攻击范围
        Set<Integer> pie = new HashSet<>();
        Set<Integer> la = new HashSet<>();
        // 回溯处理皇后摆放位置
        backtrack(result, n, 0, columns, pie, la, queens);
        return result;
    }

    private static void backtrack(List<List<String>> result,
                                  int n,
                                  int level,
                                  Set<Integer> columns,
                                  Set<Integer> pie,
                                  Set<Integer> la,
                                  int[] queens) {
        // 终止条件
        if (level == n) {
            result.add(generateBoard(queens, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 判断当前皇后所在的位置，columns表示列，pie和la表示交叉线，当存在被攻击的情况，则回溯到上一个皇后继续进行试错
            if (columns.contains(i) || pie.contains(level + i) || la.contains(level - i)) {
                continue;
            }
            // 将当前皇后攻击的范围保存
            columns.add(i);
            pie.add(level + i);
            la.add(level - i);
            // 当前皇后的位置保存
            queens[level] = i;
            // 上面代码已经确定一个皇后的位置，这个位置尝试确定下一个皇后的位置
            backtrack(result, n, level + 1, columns, pie, la, queens);
            // 消除当前层对后面层的影响参数
            queens[level] = -1;
            columns.remove(i);
            pie.remove(level + i);
            la.remove(level - i);
        }

    }

    /**
     * 生成指定类型的棋盘
     *
     * @param queens 皇后所在的位置，比如[2,4,1,3] 表示第一个皇后在第一行的第二个位置
     * @param n 皇后的个数
     * @return
     */
    private static List<String> generateBoard(int[] queens, int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 用于生成string
            char[] row = new char[n];
            // 填充默认的值
            Arrays.fill(row, '.');
            // 皇后填充Q
            row[queens[i]] = 'Q';
            // 返回值
            list.add(new String(row));
        }
        return list;
    }

}