package practice;

/**
 * 该demo是使用双向循环列表打印26个字母
 * 比如输入3，则输出 DEFG....ABC
 * 比如输入-3，则输出 XYZABCD...
 *
 * @author : tanyu
 * create at:  2021-01-13  23:12
 * @description: 双向循环链表
 */
public class PrintLetterPra {

    /**
     * 定义节点类，该节点中有前驱节点和后驱节点
     */
    static class Node {
        private String data;
        private Node prev;
        private Node next;

        public Node(Node prev, String data, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 定义操作类，屏蔽实现细节，对外提供打印字母的方法
     */
    static class OperatingClaa {
        private final static int LETTER_NUM = 26;
        private static Node head;
        private static Node last;

        /**
         * 初始化内容方法
         *
         * @return Node 返回头结点
         */
        private static void initMethod() {
            for (int i = 0; i < LETTER_NUM; i++) {
                String data = Character.toString((char) ('A' + i));
                //预先设置最后一个节点
                Node l = last;
                Node node = new Node(l, data, null);
                //新增节点永远为最后一个节点
                last = node;
                //当最后一个节点为空，则链表为空，则新节点设置为头结点
                if (l == null) {
                    //当链表存在一个节点，则头结点和尾结点是一样的
                    head = node;
                } else {
                    //链表存在元素，则将原先的尾结点设置next节点
                    l.next = node;
                }
            }
            //形成循环双向链表
            head.prev = last;
            head.prev.next = head;
        }

        /**
         * 移动节点数据
         * 负数则头结点向前移动
         * 正数则头结点向后移动
         *
         * @param index
         */
        private static Node moveNode(int index) {
            Node node = head;
            if (index > 0) {
                do {
                    node = node.next;
                } while (--index > 0);
            } else if (index < 0) {
                do {
                    node = node.prev;
                } while (++index < 0);
            }
            return node;
        }


        /**
         * 对外提供的打印方法
         *
         * @param index
         */
        public static void printMethod(int index) {
            //初始化节点数据
            initMethod();
            Node node = moveNode(index);
            for (int i = 0;i < LETTER_NUM; i++) {
                String data = node.data;
                System.out.print(data);
                node = node.next;
            }
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        OperatingClaa.printMethod(1);
    }

}