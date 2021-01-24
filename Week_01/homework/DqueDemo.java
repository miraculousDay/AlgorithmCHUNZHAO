package homework;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : tanyu
 * create at:  2021-01-23  18:04
 * @description: Deque使用
 */
public class DqueDemo {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedBlockingDeque<>();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        deque.offerFirst("D");
        System.out.println(deque.element());
        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
    }
}