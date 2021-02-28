package homework;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/word-search-ii/
 *
 * 解题思路：
 * 1.并查集加dfs
 * @see #findWords(char[][], String[])
 *
 * @author : tanyu
 * create at:  2021-02-24 16:20
 * @description: 单词搜索 II
 */
public class FindWords {
    // 四向方向移动
    private static int[][] directArr = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private static char[][] charArr = new char[][]{{'a','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
    private static String[] strArr = new String[]{"oath","pea","eat","rain"};

    public static void main(String[] args) {
        System.out.println(findWords(charArr, strArr));
    }

    /**
     * 时间复杂度：O(M*(4*3(L-1)))  l为Sting[]中最长字符串的长度 M为单元格的个数
     * 空间复杂度：O(n) 字典树的长度
     *
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        // 异常参数处理
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return result;
        }
        // words加入字典树
        Trie trie = new Trie();
        // 此处时间复杂度为：O(n*n)
        for (String word : words) {
            trie.insert(word);
        }

        // 深度优先遍历，对board单个字符上下左右进行dfs，判断是否在字典树中存在
        // 此处for循环本身时间复杂度：O(n*n)
        // 结合dfs中时间复杂度，此处的时间复杂度为：O(M*(4*3(L-1)))  l为Sting[]中最长字符串的长度 M为单元格的个数
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 时间复杂度： l为Sting[]中最长字符串的长度
                dfs(i, j, board, result, trie);
            }
        }
        return result;
    }

    /**
     * 时间复杂度：4*3(L-1) l为Sting[]中最长字符串的长度
     *
     * */
    public static void dfs(int i, int j, char[][] board, List<String> result, Trie trie) {
        // 下标异常则直接退出
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        // 如果当前字符其他位置在处理||当前字符不存在字典树中，则退出
        if (board[i][j] == '@' || trie.getNext()[board[i][j] - 'a'] == null) {
            return;
        }
        // 如果当前字符在字典树存在并且当前字符为结尾，则将字典树节点的val存入结果
        trie = trie.getNext()[board[i][j] - 'a'];
        if (trie != null && trie.getVal() != null) {
            result.add(trie.getVal());
            // 防止重复添加
            trie.setVal(null);
        }
        // 临时节点
        char temp = board[i][j];
        // 将该节点设置为正在处理
        board[i][j] = '@';
        // 四向dfs, 四向想下递归，其中三个方向是有效的，无效的是指向当前正在处理的一个方向。递归深度取决于words中字符串的长度
        // 结合以上描述，此处时间复杂度为：4*3(L-1) l为Sting[]中最长字符串的长度
        for (int[] direct : directArr) {
            dfs(i + direct[0], j + direct[1], board, result, trie);
        }
        // 处理当前改变变量，还原成原有值
        board[i][j] = temp;
    }


    // 执行效率高的代码
    class Solution {

        public List<String> findWords(char[][] board, String[] words) {
            List<String> result = new ArrayList<>();
            TrieNode trie = new TrieNode();
            for (String word : words) {
                TrieNode node = trie;
                for (char letter : word.toCharArray()) {
                    node.add(letter);
                    node = node.get(letter);
                }
                node.setEnd(true);
                node.setWord(word);
            }
            int row = board.length, col = board[0].length;
            boolean[][] valid = new boolean[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    findWords(i, j, board, valid, trie, new ArrayList<Character>(), result);
                }
            }
            return result;
        }

        private void findWords(int curRow, int curCol, char[][] board, boolean[][] valid, TrieNode trie,
                               List<Character> list, List<String> result) {
            int row = board.length, col = board[0].length;
            if (curRow < 0 || curRow == row || curCol < 0 || curCol == col || valid[curRow][curCol]
                    || !trie.contains(board[curRow][curCol])) {
                return;
            }
            TrieNode node = trie.get(board[curRow][curCol]);
            list.add(board[curRow][curCol]);
            valid[curRow][curCol] = true;
            if (node.getEnd()) {
                node.setEnd(false);
                result.add(node.getWord());
                if (node.childrenCount == 0) {
                    trie.delete(board[curRow][curCol]);
                }
            }
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            for (int[] direction : directions) {
                findWords(curRow + direction[0], curCol + direction[1], board, valid, node, list, result);
                if (node.childrenCount == 0) {
                    trie.delete(board[curRow][curCol]);
                }
            }
            list.remove(list.size() - 1);
            valid[curRow][curCol] = false;
        }

        public class TrieNode {

            private final int nodeSize = 26;

            public TrieNode[] children;
            public int childrenCount;
            public boolean end;
            public String word;

            public TrieNode() {
                children = new TrieNode[nodeSize];
                childrenCount = 0;
                end = false;
                word = null;
            }

            public void add(char c) {
                int index = getIndex(c);
                if (children[index] == null) {
                    children[index] = new TrieNode();
                    childrenCount++;
                }
            }

            public boolean contains(char c) {
                return children[getIndex(c)] != null;
            }

            public TrieNode get(char c) {
                if (!contains(c)) {
                    throw new IllegalArgumentException("the letter " + c + " isn't in the node");
                }
                return children[getIndex(c)];
            }

            public void delete(char c) {
                int index = getIndex(c);
                if (children[index] != null) {
                    children[index] = null;
                    childrenCount--;
                }
            }

            public void setEnd(boolean end) {
                this.end = end;
            }

            public boolean getEnd() {
                return end;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getWord() {
                return word;
            }

            private int getIndex(char c) {
                return c - 'a';
            }

        }
    }
}
