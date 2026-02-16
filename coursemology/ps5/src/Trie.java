import java.util.ArrayList;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';
    private TrieNode root;

    private static final int CHARSET_SIZE = 62;

    private class TrieNode {
        TrieNode[] children = new TrieNode[CHARSET_SIZE];
        boolean isWord;
    }

    public Trie() {
        root = new TrieNode();
    }

    private int charToIndex(char c) {
        if (c >= 'a' && c <= 'z') return c - 'a';
        if (c >= 'A' && c <= 'Z') return 26 + (c - 'A');
        if (c >= '0' && c <= '9') return 52 + (c - '0');
        return -1;
    }

    private char indexToChar(int i) {
        if (i < 26) return (char) ('a' + i);
        if (i < 52) return (char) ('A' + (i - 26));
        return (char) ('0' + (i - 52));
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = charToIndex(s.charAt(i));
            if (idx == -1) continue;
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isWord = true;
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = charToIndex(s.charAt(i));
            if (idx == -1 || curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.isWord;
    }


    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<>();
        dfs(root, s, 0, new StringBuilder(), results, limit);
        return results.toArray(new String[0]);
    }

    private void dfs(
            TrieNode node,
            String pattern,
            int pos,
            StringBuilder path,
            ArrayList<String> results,
            int limit) {

        if (node == null || results.size() >= limit) return;

        if (pos == pattern.length()) {
            collect(node, path, results, limit);
            return;
        }

        char c = pattern.charAt(pos);

        if (c == WILDCARD) {
            for (int i = 0; i < CHARSET_SIZE; i++) {
                if (node.children[i] != null) {
                    path.append(indexToChar(i));
                    dfs(node.children[i], pattern, pos + 1, path, results, limit);
                    path.deleteCharAt(path.length() - 1);
                }
            }
        } else {
            int idx = charToIndex(c);
            if (idx == -1 || node.children[idx] == null) return;
            path.append(c);
            dfs(node.children[idx], pattern, pos + 1, path, results, limit);
            path.deleteCharAt(path.length() - 1);
        }
    }

    private void collect(
            TrieNode node,
            StringBuilder path,
            ArrayList<String> results,
            int limit) {

        if (node == null || results.size() >= limit) return;

        if (node.isWord) {
            results.add(path.toString());
        }

        for (int i = 0; i < CHARSET_SIZE; i++) {
            if (node.children[i] != null) {
                path.append(indexToChar(i));
                collect(node.children[i], path, results, limit);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();

        t.insert("A12a");
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("a09");

        System.out.println(t.contains("A12a")); // true
        System.out.println(t.contains("a09"));  // true
        System.out.println(t.contains("xyz"));  // false

        String[] result = t.prefixSearch("pe", 10);
        System.out.println(java.util.Arrays.toString(result));
    }
}
