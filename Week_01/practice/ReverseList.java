package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 解题思路：
 * 1.新建一个链表，依次访问源链表，在新链表中使用头插法
 * @see #reverseList_1(ListNode)
 *
 * 2.双指针，一前一后，后面的next指向前面的节点
 * @see #reverseList_2(ListNode)
 *
 * 3.妖魔化的双指针，有点类似于快慢指针。
 * @see #reverseList_3(ListNode)
 *
 * @author : tanyu
 * create at:  2021-01-21  23:34
 * @description: 反转链表
 */
public class ReverseList {

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList_3(ListNode head) {
        ListNode cur = head;
        while (head.next != null) {
            // 预先变量，用于交换
            ListNode temp = head.next.next;
            // 指针反转
            head.next.next = cur;
            // 变量交换
            cur = head.next;
            head.next = temp;
        }
        return cur;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList_2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (head != null) {
            // 预先变量，用于交换
            ListNode temp = head.next;
            // 指向前一个节点
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList_1(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            newHead = new ListNode(head.val, newHead);
            head = head.next;
        }
        return newHead;
    }

    static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

}