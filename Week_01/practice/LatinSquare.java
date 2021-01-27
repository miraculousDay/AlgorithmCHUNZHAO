package practice;

/**
 * 一个nxn的矩阵，给定n个元素，每个元素个数有n个
 * 形成如下矩阵:
 * 1 2 3
 * 2 3 1
 * 3 1 2
 * 每行都有n个元素，每列都有n个元素，且不重复出现。
 * <p>
 * 思路：拉丁矩阵规则是按照n的顺序按个排列，
 * 比如 1 2 3 这样的排列，其实是相当于链表，按个对整个n进行一遍读取
 * 2 3 1
 * 3 1 2
 *
 * @author : tanyu
 * create at:  2021-01-12  23:13
 * @description: 拉丁方阵
 */
public class LatinSquare {

    /**
     * 在控制台打印出拉丁矩阵
     * <p>
     * 解题思路：
     * 预先设置变量k，表示第二个循环中需要打印的元素极限数字，
     * k会随着外部for依次减少，f内部for则打印出1到k-1之间的数字
     * 外部for循环中，while条件打印k~n的数字。
     *
     * @param n 拉丁矩阵的维度
     */
    private static void printLatin(int n) {
        //极限数字
        int k = n + 1;
        for (int i = 1; i <= n; i++) {
            //临时值
            int temp = k;
            //当k减少之后则，打印k-n的数字
            while (temp <= n) {
                System.out.print(temp + " ");
                temp++;
            }
            //后面则打印小于k的数字
            for (int j = 1; j < k; j++) {
                System.out.print(j + " ");
            }
            k--;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printLatin(6);
    }
}