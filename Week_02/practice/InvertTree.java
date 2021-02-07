package practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/invert-binary-tree/description/
 *
 * 解题思路：
 * 1.递归
 *
 * @author : tanyu
 * create at:  2021-02-06  10:19
 * @description: 翻转二叉树
 */
public class InvertTree {


    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 最近子问题是需要交换当前节点的左右子树
     * 当前使用代码的思想是深度优先遍历
     *
     * @param root
     * @return
     */
    public PostorderTraversal.TreeNode invertTree_1(PostorderTraversal.TreeNode root) {
        if (root == null) {
            return null;
        }
        PostorderTraversal.TreeNode left = invertTree_1(root.left);
        PostorderTraversal.TreeNode right = invertTree_1(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 采用广度优先遍历，用栈维护节点的出入顺序。
     *
     * @param root
     * @return
     */
    public PostorderTraversal.TreeNode invertTree_2(PostorderTraversal.TreeNode root) {
        if (root == null) {
            return null;
        }
        // 根节点元素先进栈
        Queue<PostorderTraversal.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 获取栈顶元素
            PostorderTraversal.TreeNode temp = queue.poll();
            // 交换左右节点
            PostorderTraversal.TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            // 将左右节点依次入栈，然后再交换左右节点位置
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
        }
        return root;
    }
}