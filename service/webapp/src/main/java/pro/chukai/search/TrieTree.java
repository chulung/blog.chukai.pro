package pro.chukai.search;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 用于实时查询的字典树
 *
 * @author chukai
 */
public class TrieTree {

    /**
     * 根节点
     */
    private Node root;

    public TrieTree() {
        root = new Node();
        root.children = new HashMap<>();
    }

    /**
     * 根据给定词语完全前缀匹配查询
     *
     * @param prefixe 前缀
     * @return 匹配该前缀的字符串
     */
    public List<String> prefixeMatching(String prefixe) {
        if (StringUtils.isBlank(prefixe)) {
            return Collections.emptyList();
        }
        char[] chars = prefixe.toCharArray();
        List<String> strings = prefixeMatching("", root, chars, 0);
        return strings == null ? Collections.emptyList() : strings;
    }

    private List<String> prefixeMatching(String curPrefixe, Node node, char[] chars, int charIndex) {
        Map<Character, Node> children = node.children;
        Node childNode = children.get(chars[charIndex]);
        if (childNode == null) {
            return null;
        }
        if (charIndex < chars.length - 1) {
            return prefixeMatching(curPrefixe + childNode._char, childNode, chars, ++charIndex);
        } else {
            ArrayList<String> words = new ArrayList<>();
            spreadNodes(curPrefixe + childNode._char, childNode.isEndOfWord, childNode.children, words);
            return words;
        }
    }

    /**
     * 将该节点下所有词汇展开
     *
     * @param prefixe
     * @param isEndOfWord
     * @param children
     * @param words
     */
    private void spreadNodes(String prefixe, boolean isEndOfWord, Map<Character, Node> children, List<String> words) {
        if (isEndOfWord) {
            words.add(prefixe);
        }
        if (children != null && !children.isEmpty()) {
            for (Node node : children.values()) {
                spreadNodes(prefixe + node._char, node.isEndOfWord, node.children, words);
            }
        }
    }

    /**
     * 插入词汇
     *
     * @param word
     */
    public void insert(String word) {
        if (StringUtils.isBlank(word)) {
            return;
        }
        char[] chars = word.toCharArray();
        insertNode(root, chars, 0);
    }

    private void insertNode(Node node, char[] chars, int charIndex) {
        //插入完毕，该节点标识为词汇结尾
        if (charIndex == chars.length) {
            node.isEndOfWord = true;
            return;
        }
        char c = chars[charIndex];
        Node childNode = node.children.get(c);
        if (childNode == null) {
            childNode = new Node();
            childNode.children = new HashMap<>(1);
            childNode._char = c;
            node.children.put(c, childNode);
        }
        insertNode(childNode, chars, ++charIndex);
    }

    public boolean delete(String word) {
        char[] chars = word.toCharArray();
        return findAndDelete(root, chars, 0);
    }

    private boolean findAndDelete(Node node, char[] chars, int charIndex) {
        char c = chars[charIndex];
        Node childNode = node.children.get(c);
        if (childNode == null) {
            return false;
        }
        if (charIndex == chars.length - 1) {
            if (childNode.children == null || childNode.children.isEmpty()) {
                node.children.remove(c);
            } else {
                childNode.isEndOfWord = false;
            }
            return true;
        }
        return findAndDelete(childNode, chars, ++charIndex);
    }

    private class Node {
        char _char;
        Map<Character, Node> children;
        /**
         * 是否为词汇结尾
         */
        boolean isEndOfWord;
    }

}
