package playground;

import data.PrefixTrie;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        PrefixTrie trie = new PrefixTrie();

        trie.insertPrefix("sup");
        trie.insertPrefix("schachte");
        trie.insertPrefix("sum");

        List<String> results = trie.prefixDfs("sup");

    }
}
