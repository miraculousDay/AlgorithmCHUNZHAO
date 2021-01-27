package practice;

import java.util.Random;

/**
 * 在约瑟夫问题的基础上，自杀的每个人拥有一个正整数M，当这个
 * 自杀之后，这个正整数M将作为报数号，当报数到M的时候，则下个人自杀，
 * 自杀人所持有的数字将作为下一个报数号。
 *
 * @author : tanyu
 * create at:  2021-01-18  22:12
 * @description: 约瑟夫问题变式1
 */
public class JosephusProblem_v1 {

    /**
     * 节点数量
     */
    private final static int NODE_COUNTS = 10;

    /**
     * 用于产生随机数字
     */
    private final static Random ran = new Random();

    public static void main(String[] args) {
        //1.初始化节点内容
        Node head = initNodes();
        //2.打印自杀顺序
        killOrder(head);
    }

    private static void killOrder(Node head) {
        //当前节点
        Node cur = head;
        //当前节点的上一个节点，用于删除节点的时候，处理next的问题
        Node pre = null;
        //第一个自杀数字
        int killNum = ran.nextInt(NODE_COUNTS);
        //报数计算器
        int count = 1;
        while (head.next != head) {
            //当报数达到自杀数字
            if (count == killNum) {
                System.out.print(cur.data + " ");
                //重新获取自杀报数
                killNum = cur.data;
                //报数计算器重置
                count = 1;
                pre.next = cur.next;
            } else {
                pre = cur;
                count++;
            }
            head = head.next;
            cur = cur.next;
        }
    }

    /**
     * 初始化节点内容
     *
     * @return 返回头结点
     */
    private static Node initNodes() {
        Node head = null;
        Node last = null;
        for (int i = 0; i < NODE_COUNTS; i++) {
            int data = ran.nextInt(NODE_COUNTS);
            System.out.print(data + " ");
            Node l = last;
            Node node = new Node(data, null);
            last = node;
            if (l == null) {
                head = node;
            } else {
                l.next = node;
            }
        }
        last.next = head;
        return head;
    }

    /**
     * 节点数据
     */
    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}