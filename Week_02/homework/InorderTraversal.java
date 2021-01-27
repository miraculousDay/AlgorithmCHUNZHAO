package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 *
 * 解题思路：
 * 1.递归
 * @see #inorderTraversal_1(TreeNode)
 *
 * 2.遍历，手动维护一个栈，来处理中序遍历的顺序
 * @see #inorderTraversal_2(TreeNode)
 *
 * 3.莫里斯中序遍历
 * @see #inorderTraversal_3(TreeNode)
 *
 * @author : tanyu
 * create at:  2021-01-26 15:51
 * @description: 二叉树的中序遍历
 */
public class InorderTraversal {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 处理逻辑：
     * 1.先判断当前节点是否有左节点，没有则将当前节点值方加入结果，将节点指向右侧节点
     * 2.第1步中存在左节点，则找到左边树x的最右边的节点，记做predecessor，
     * 如果predecessor的右节点为空，则predecessor的右节点指向x，x则指向左节点。
     * 如果predecessor的右节点不为空，将x的值加入结果，x则指向右节点。predecessor的右节点则置位空
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // 指示节点
        TreeNode predecessor = null;
        while (root != null) {
            // 判断左子树是否存在
            if (root.left != null) {
                predecessor = root.left;
                // 找到当前节点最右的节点，predecessor.
                // right != root判断表示predecessor是新的predecessor节点
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // 新predecessor
                if (predecessor.right == null) {
                    // predecessor右子树指向root
                    predecessor.right = root;
                    // 继续往左子树遍历
                    root = root.left;
                } else {
                    // 旧的predecessor，则将root值加入结果
                    result.add(root.val);
                    root = root.right;
                    // 将破坏树结构的连接点去掉
                    predecessor.right = null;
                }
            } else {
                //没有左子树则将则值加入结果，并指向右节点
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 手动维护一个栈，
     * 1.依次将根节点、左子树、左子树跟节点全部入栈
     * 2.然后取出最底部的左节点，获取该节点的值，
     * 3.获取相应的跟节点，获取该根节点的值
     * 4.获取3位置的右子树，当右子树存在左右子树(即为子树跟节点，则循环1、2、2的操作)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root);
        return result;
    }

    public void helper(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        // 中序遍历结构，左根右
        helper(result, root.left);
        result.add(root.val);
        helper(result, root.right);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
