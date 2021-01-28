package homework;

import java.util.*;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * 解题思路：
 * 1.迭代，广度优先遍历
 * @see #levelOrder_1(Postorder.Node)
 *
 * 2.迭代，简化的广度优先搜索
 * @see #levelOrder_2(Postorder.Node)
 *
 * 3.递归
 * @see #levelOrder_3(Postorder.Node)
 *
 * @author : tanyu
 * create at:  2021-01-28 14:21
 * @description: 叉树的层序遍历
 */
public class LevelOrder {

    List<List<Integer>> result = new ArrayList<>();
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：正常情况 O(logn)，最坏情况 O(n)O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_3(Postorder.Node root) {
        if (root != null) {
            helper(root, 0);
        }
        return result;
    }

    /**
     * 递归体：递归是深度遍历，每个层级执行的操作一样的，将结果加入结果集，带上level表示当前层级，
     * 当前层级level是在List的下标
     * @param root
     * @param level
     */
    public void helper(Postorder.Node root, int level) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        for (Postorder.Node child : root.children) {
            helper(child, level + 1);
        }
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_2(Postorder.Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 预先将根节点加入list
        List<Postorder.Node> preList = Arrays.asList(root);
        while (!preList.isEmpty()) {
            // 层级节点保存数据
            List<Integer> level = new ArrayList<>();
            List<Postorder.Node> curLit = new ArrayList<>();
            // 将节点的值加入level
            for (Postorder.Node item : preList) {
                level.add(item.val);
                // 新节点加入新的list
                curLit.addAll(item.children);
            }
            // 当前层次数组放入结果集
            result.add(level);
            // 下一层的所有节点变量置换
            preList = curLit;
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
    public List<List<Integer>> levelOrder_1(Postorder.Node root) {
        // 结果
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 双端队列
        Queue<Postorder.Node> stack = new LinkedList<>();
        // 预先将根节点入队列
        stack.offer(root);
        while (!stack.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 当前队列的长度，表示当前层级的节点个数
            int levelSize = stack.size();
            // 遍历当前节点的值，然后将节点对应的子节点加入队列
            for (int i = 0; i < levelSize; i++) {
                Postorder.Node poll = stack.poll();
                level.add(poll.val);
                stack.addAll(poll.children);
            }
            result.add(level);
        }
        return result;
    }
}
