package homework;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 * 解题思路：
 * 字典树：又称单词查找树或键树，是一种典型的树状结构，典型用于统计和排序大量字符。
 * 该树状根节点为空，节点与节点之间的连线标识为字符，节点内容一般为空或者用于统计字符出现次数
 *
 * 特点：
 * 1.本身不存完整词语
 * 2.从根节点到某个节点，路径（边劲）走的字符组合为某节点处的单词
 * 3.每个节点走过路径表示的字符都不相同
 *
 * 核心思想：
 * 1.空间换时间
 * 2.可以利用公共字符前缀来降低查询时间的开销
 *
 *
 * @author : tanyu
 * create at:  2021-02-24 14:15
 * @description: 实现 Trie (前缀树)
 */
public class Trie {
    private Trie[] next;
    // 是否是结尾标识
    private boolean isEnd;
    // 结尾标识时，将字符设置为节点的值
    private String val;

    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Trie cur = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Trie();
            }
            cur = cur.next[index];
        }
        cur.val = word;
        isEnd = true;
    }


    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }


    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String word) {
        if (word == null || word.length() == 0) {
            return null;
        }
        Trie cur = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            cur = cur.next[words[i] - 'a'];
            if (cur == null) return null;
        }
        return cur;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        String word = "abc";
        String word1 = "abd";
        String word2 = "aef";
        String word3 = "eef";
        obj.insert(word);
        obj.insert(word1);
        obj.insert(word2);
        obj.insert(word3);
        System.out.println(obj.startsWith("ab"));
    }

    public Trie[] getNext() {
        return next;
    }

    public void setNext(Trie[] next) {
        this.next = next;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
