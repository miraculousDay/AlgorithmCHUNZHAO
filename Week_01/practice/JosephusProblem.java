package practice;

/**
 * 约瑟夫问题：
 * 约瑟夫和他的朋友以及39个犹太人，在城市被占领之后决定自杀，
 * 他们约定报数来决定自杀顺序，所有人排成一个圈，从1开始报数，当
 * 报到3的时候，则这个人自杀，然后重新从1开始。
 * <p>
 * 但是约瑟夫和他的朋友不愿意自杀，由于约瑟夫和他的朋友将自己安排在
 * 16和31这两位置。
 * <p>
 * 以下程序是使用单向循环输出自杀的顺序
 *
 * @author : tanyu
 * create at:  2021-01-18  21:20
 * @description: 约瑟夫问题
 */
public class JosephusProblem {
    /**
     * 节点个数
     */
    private final static int NODE_COUNTS = 41;
    /**
     * 自杀的数字
     */
    private final static int KILL_ORDER = 3;

    public static void main(String[] args) {
        //1.初始化节点内容
        Node head = initNodes();
        //2.输入自杀顺序
        killOrder(head);
    }

    /**
     * 输出自杀顺序
     * @param head
     */
    private static void killOrder(Node head) {
        //计算，当算到自杀报数的时候，重置为为1
        int count = 1;
        //用于移除要删除节点，该节点的下一个将指向要删除节点的下一个节点
        Node prev = null;
        //当前节点
        Node cur = head;
        //当前节点的个数
        int limitCount = NODE_COUNTS;
        //循环结束条件
        while (head.next != head) {
            //当小于自杀报数的时候，则停止自杀
            if (limitCount < KILL_ORDER) {
                return;
            }
            //报数达到自杀数字
            if (count == KILL_ORDER) {
                //规避当第一个自杀数字为1的时候，prev为空，prev.next会出现空指针异常;
                if (prev != null) {
                    prev.next = cur.next;
                }
                System.out.println(cur.data);
                limitCount--;
                //重置计算器
                count = 1;
            } else {
                //设置上一个节点内容
                prev = cur;
                count++;
            }
            //设置下一个节点
            cur = cur.next;
            head = head.next;
        }

    }

    /**
     * 初始化节点内容
     * @return
     */
    private static Node initNodes() {
        //头结点
        Node head = new Node(1, null);
        //下一个节点作为尾结点
        Node next = head;
        for (int i = 2; i <= NODE_COUNTS; i++) {
            Node temp = new Node(i, null);
            next.next = temp;
            next = next.next;
        }
        //尾节点指向头部，形成单向循环链表
        next.next = head;
        return head;
    }

    /**
     * 单链表节点类
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