package practice;

/**
 * leetcode连接：
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * 
 * 解题思路：
 * 1.迭代方法.
 * @see #swapPairs_1(ReverseList.ListNode)
 *
 * 2.递归方法
 * @see #swapPairs_2(ReverseList.ListNode)
 * 
 * @author : tanyu
 * create at:  2021-01-25  11:25
 * @description: 两两交换链表中的节点
 */
public class SwapPairs {

    /**
     * 递归暂时为理解
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) 递归的深度
     *
     * @param head
     * @return
     */
    public ReverseList.ListNode swapPairs_2(ReverseList.ListNode head) {
        if (head != null && head.next != null) {
            return null;
        }
        ReverseList.ListNode newHead = head.next;
        head.next = swapPairs_2(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ReverseList.ListNode swapPairs_1(ReverseList.ListNode head) {
        ReverseList.ListNode dump = new ReverseList.ListNode(0, null);
        // 在原有链表头部增加一个节点,dump用于return返回
        dump.next = head;
        // 用于迭代
        ReverseList.ListNode temp = dump;
        // 需要那到第一个和第二个节点进行兑换
        while (temp.next != null && temp.next.next != null) {
            //temp为不需要换位置的节点
            //第一个节点
            ReverseList.ListNode node1 = temp.next;
            //第二个节点
            ReverseList.ListNode node2 = temp.next.next;
            // temp指第二个节点
            temp.next = node2;
            // 第一个节点的下一个节点指向第二个节点的下一个节点
            node1.next = node2.next;
            // 第二个节点的下一个节点指向第一个节点
            node2.next = node1;
            // 上面完成第一个节点和第二个节点互换操作
            // 移动临时的节点到第二个节点位置，这样该节点位置的后面两个节点是需要交换位置的节点
            temp = node1;
        }
        return dump.next;
    }


}
