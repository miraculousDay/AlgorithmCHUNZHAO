package practice;

import java.util.Stack;

/**
 * leetcode链接：
 * https://leetcode-cn.com/problems/min-stack/
 *
 * 解题思路：
 * 1.辅助栈，一个数组栈，一个辅助栈
 * @see MinStack_1
 *
 * 2.使用链表，链表中保存在当前插入元素的时候的最小值，插入元素时候使用头插法
 * @see MinStack_2
 *
 * @author : tanyu
 * create at:  2021-01-23  21:53
 * @description: 最小栈
 */
public class MinStack {

    public static void main(String[] args) {

    }


    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(n)
     */
    static class MinStack_2 {
        private Node head;


        public void push(int x) {
            if (head == null) {
                head = new Node(x, x, null);
            } else {
                //头插法插入节点，并计算当前插入的最小值
                head = new Node(x, Math.min(x, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.data;
        }

        public int getMin() {
            return head.min;
        }

        /**
         * 节点类，保存当前值，当前元素插入时候的最小值，下一个节点
         */
        class Node {
            int data;
            int min;
            Node next;

            public Node(int data, int min, Node next) {
                this.data = data;
                this.min = min;
                this.next = next;
            }
        }
    }

    /**
     * 时间复杂度：O(1) stack出栈和入栈的操作都是O(1),getMin也是从minStack栈顶取，所以总体为O(1)
     * 空间复杂度：O(n)
     */
    static class MinStack_1 {
        //数据栈
        private Stack<Integer> dataStack;
        //最小值辅助栈
        private Stack<Integer> minStack;

        public MinStack_1() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {

            dataStack.add(x);
            //首次入栈，则直接添加元素
            if (minStack.isEmpty()) {
                minStack.add(x);
            } else {
                //不然就则与上一个元素比较，一直保存当前栈中已有元素下最小的值
                minStack.add(Math.min(x, minStack.peek()));
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}