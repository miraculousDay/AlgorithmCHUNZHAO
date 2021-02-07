package homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode链接
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description
 *
 * 解题思路
 * 1.广度优先遍历
 * @see #largestValues_1(TreeNode)
 *
 * 2.广度优先遍历 带level
 * @see #largestValues_2(TreeNode)
 *
 * @author : tanyu
 * create at:  2021-02-07  21:15
 * @description: 在每个树行中找最大值
 *
 */
public class LargestValues {

    /**
     * 时间复杂度：O(n)
     * 空间负责度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result, 1);
        return result;
    }

    private void helper(TreeNode root, List<Integer> result, int level) {
        if (root == null) {
            return;
        }
        // 此处把根节点当初第一层
        if (level == result.size() + 1) {
            result.add(root.val);
        } else {
            // 比较同层中大小的值
            result.set(level - 1, Math.max(result.get(level - 1), root.val));
        }
        helper(root.left, result, level + 1);
        helper(root.right, result, level + 1);
    }


    /**
     * 时间复杂度：O(n)
     * 空间负责度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer max = null;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                max = max == null? temp.val : Math.max(max, temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    static class TreeNode {
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