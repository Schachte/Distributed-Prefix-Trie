package playground;

import data.PrefixTrie;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        PrefixTrie trie = new PrefixTrie();

        trie.insertPrefixRecursive("sup");
        trie.insertPrefixRecursive("schachte");
        trie.insertPrefixRecursive("sum");

        List<String> results = trie.prefixDfs("sup");

    }
}
