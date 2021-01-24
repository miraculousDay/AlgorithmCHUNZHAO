package homework;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * 解题思路：
 * 1.两个链表按个比较值并再排序是最近的子问题，递归比较两个链表当前节点的大小
 * @see #mergeTwoLists_1(ListNode, ListNode)
 *
 * 2.遍历，新建一个ListNode链表，遍历源链表，在遍历中比较，小的作为节点加入新的链表。
 * @see #mergeTwoLists_2(ListNode, ListNode)
 *
 * @author : tanyu
 * create at:  2021-01-24  15:59
 * @description: 合并两个有序链表
 *
 *
 * 2.
 */
public class MergeTwoLists {

    /**
     * 时间复杂度：O(n + m))   m：表示l1的节点个数  n：表示l2的节点个数
     * 空间复杂度：O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        //预先定义好头节点,用于返回结果
        ListNode preHead = new ListNode(-1, null);
        //指针节点
        ListNode pre = preHead;
        //对l1、l2进行迭代
        while (l1 != null && l2 != null) {
            //比较节点的值
            if (l1.val > l2.val) {
                pre.next = l2;
                l2 = l2.next;
            } else {
                pre.next = l1;
                l1 = l1.next;
            }
            //指向到下一个节点，方便增加节点
            pre = pre.next;
        }
        //处理l1或l2多出来的部分
        pre.next = l1 == null ? l2 : l1;
        return preHead.next;
    }


    /**
     * 时间复杂度：O(m + n) m：表示l1的节点个数  n：表示l2的节点个数
     * 空间复杂度：O(m + n) m：表示l1的节点个数  n：表示l2的节点个数
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {
        //为空为退出条件
        if (l1 == null) {
            return l1;
        } else if (l2 == null) {
            return l2;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_1(l1, l2.next);
            return l2;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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