package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode连接：
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 解题思路：
 * 1.迭代方法，使用Java map
 * @see #detectCycle_1(ReverseList.ListNode)
 *
 * 2.快慢指针
 * @see #detectCycle_2(ReverseList.ListNode)
 *
 * @author : tanyu
 * create at:  2021-01-25  15:20
 * @description: 环形链表 II
 * @see
 */
public class DetectCycle {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) 链表节点的个数
     *
     * @param head
     * @return
     */
    public ReverseList.ListNode detectCycle_1(ReverseList.ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Map<ReverseList.ListNode, Integer> hash = new HashMap<>();
        while (head != null) {
            // 存在环，则返回当前环的第一个
            if(hash.putIfAbsent(head, 1) != null) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ReverseList.ListNode detectCycle_2(ReverseList.ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 快慢指针定位是否有环
        ReverseList.ListNode slow = head;
        ReverseList.ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 确定了环之后，重新从头部开始一个走一步的指针，已存在的慢指针从相遇的节点继续走
            if (slow == fast) {
                // 新的慢指针
                ReverseList.ListNode newSlow = head;
                // 最终旧的慢指针和新的慢指针会在环的入口处相遇
                while (newSlow != slow) {
                    newSlow = newSlow.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
