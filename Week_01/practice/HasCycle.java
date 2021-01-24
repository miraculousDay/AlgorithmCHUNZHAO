package practice;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/linked-list-cycle/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * 解题思路：
 * 1。快慢指针，慢指针走一步，快指针走两步，当相遇则表示存在环。
 * @see #hasCycle_1(ReverseList.ListNode)
 *
 * 2.用hash，Java中利用set的不允许出现重复元素进行处理。
 * @see #hasCycle_2(ReverseList.ListNode)
 *
 * @author : tanyu
 * create at:  2021-01-24  23:27
 * @description: 检查链表是否有环
 */
public class HasCycle {

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) 节点个数
     *
     * @param head
     * @return
     */
    public boolean hasCycle_2(ReverseList.ListNode head) {
        if (head == null) {
            return false;
        }
        // 利用不重复元素的特性
        Set<ReverseList.ListNode> hash = new HashSet<>();
        while (head != null) {
            if (!hash.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(2)
     *
     * @param head
     * @return
     */
    public boolean hasCycle_1(ReverseList.ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 快指针从头结点的下一个节点开始
        ReverseList.ListNode fast = head.next;
        // 慢指针从头节点开始
        ReverseList.ListNode slow = head;
        // 不相遇则继续往下走
        while (fast != slow) {
            // 非空判断
            if (fast == null || fast.next == null) {
                return false;
            }
            //快指针走两步
            fast = fast.next.next;
            // 慢指针走一个
            slow = slow.next;
        }
        return true;
    }
}