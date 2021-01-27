package practice;

/**
 * 魔术师数从手中的牌中抽取牌，数到1，则抽出第一张牌，翻开为黑桃A，
 * 然后继续从1开始数，数到2，翻出第二张，之前的牌放在剩下的牌的底部，
 * 第二张牌为黑桃2，依次将黑套的牌全部抽出。
 * <p>
 * 该段代码默认为13张牌，然后按照上面魔术师发牌规则进行处理。
 * 使用单向循环链表
 *
 * @author : tanyu
 * create at:  2021-01-18  22:16
 * @description: 魔术师发牌问题
 */
public class MagicCards {

    private final static int NODES_COUNT = 13;

    public static void main(String[] args) {
        //1.初始化节点信息,
        Node last = initNodes();
        Node node = last.next;
        //2.按照魔术师发牌规则对节点数据进行处理
        nodeDataInit(last);
        for (int i = 0; i < NODES_COUNT; i++) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    /**
     * 通过尾部节点来处理节点数据初始化
     *
     * @param last 尾节点
     */
    private static void nodeDataInit(Node last) {
        //循环次数的计算器
        int count = 1;
        while (count <= NODES_COUNT) {
            int index = count;
            //获取翻牌的次数
            for (int i = 0; i < index; i++) {
                last = last.next;
                //如果不为0，表示这个位置已经被占，需要重新找下一个
                if (last.data != 0) {
                    i--;
                }
            }
            //节点赋值
            last.data = index;
            count++;
        }
    }

    /**
     * 节点数据吃初始化
     *
     * @return 尾节点
     */
    private static Node initNodes() {
        Node head = null;
        Node last = null;
        for (int i = 0; i < NODES_COUNT; i++) {
            Node l = last;
            Node node = new Node(0, null);
            last = node;
            if (l == null) {
                head = node;
            } else {
                l.next = node;
            }
        }
        //使得形成循环链表
        last.next = head;
        return last;
    }


    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}