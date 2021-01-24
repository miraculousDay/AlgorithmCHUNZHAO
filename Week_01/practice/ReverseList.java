package practice;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 1.新建一个链表，依次访问源链表，在新链表中使用头插法
 *
 *
 * @author : tanyu
 * create at:  2021-01-21  23:34
 * @description: 反转链表
 */
public class ReverseList {

    public static void main(String[] args) {

    }

    static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            newHead = new ListNode(head.val, newHead);
            head = head.next;
        }
        return newHead;
    }
}