package data;

import java.util.*;

/**
 * Implementation of a prefix trie that will hold ranges of X - Y
 * in memory when performing lookups.
 */
public class PrefixTrie {

    /**
     * Node representation for each node to
     * represent a single value of a prefix and the parent-child associations.
     */
    public static class TrieNode {
        protected Map<Character, TrieNode> children;
        protected boolean eow;

        public TrieNode() {
            children = new HashMap<>();
            eow = false;
        }
    }

    private final TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    /**
     * Efficient phrase insertion
     *
     * @param phrase Input phrase to be inserted into the prefix trie
     */
    public void insertPrefixRecursive(String phrase) {
        insertPrefixRecursive(root, phrase, 0);
    }

    private void insertPrefixRecursive(TrieNode curr, String phrase, int index) {
        if (phrase.length() == index) {
            curr.eow = true;
            return;
        }

        char currChar = phrase.charAt(index);
        TrieNode currNode = curr.children.get(currChar);

        if (currNode == null) {
            currNode = new TrieNode();
            curr.children.put(currChar, currNode);
        }

        insertPrefixRecursive(currNode, phrase, index + 1);
    }

    /**
     * Does a depth-first prefix pattern match against the trie recursively
     *
     * @param phrase is the prefix of the phrase to match against
     * @return List of strings for all words that match the given prefix
     */
    public List<String> prefixDfs(String phrase) {
        TrieNode subtree = findSubtree(phrase);

        if (subtree != null) {
            return prefixDfs(subtree, new ArrayList<>(), "", phrase);
        }
        return Collections.emptyList();
    }

    private List<String> prefixDfs(TrieNode node, List<String> result, String currWord, String phrase) {
        if (node.eow) {
            result.add(phrase + currWord);
        }

        // Final check to determine leaf or not
        if (node.children.size() == 0) {
            return result;
        }

        for (Character child : node.children.keySet()) {
            prefixDfs(node.children.get(child), result, currWord + child, phrase);
        }

        return result;
    }


    /**
     * This will find a subtree within the tree to do pattern matching of phrase prefixes
     *
     * @param phrase Prefix phrase
     * @return TrieNode that matches the find of a particular trie subtree
     */
    public TrieNode findSubtree(String phrase) {
        return findSubtree(phrase, 0, getRoot());
    }

    private TrieNode findSubtree(String phrase, int index, TrieNode currNode) {
        if (phrase.length() == 0 || index == phrase.length()) {
            return currNode;
        }

        TrieNode tmp = currNode.children.get(phrase.charAt(index));

        // The node doesnt exist within the tree
        if (tmp == null) return null;

        return findSubtree(phrase, index + 1, tmp);
    }
}
