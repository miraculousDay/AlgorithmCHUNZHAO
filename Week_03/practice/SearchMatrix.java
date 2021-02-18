package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 * 解题思路
 * 1.二分查找，此题中二维数组中每个数组元素都是有序的，因此可以采用二分查找
 * @see #searchMatrix(int[][], int)
 *
 * @author : tanyu
 * create at:  2021-02-18 15:20
 * @description:  搜索二维矩阵
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 行数
        int row = matrix.length;
        // 列数
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        int mid = 0;
        while (left <= right) {
            // 中间值
            mid = left + (right - left) / 2;
            // mid / col获取行  mid % col获取列
            if (matrix[mid / col][mid % col] == target) {
                return true;
            }
            if (matrix[mid / col][mid % col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
