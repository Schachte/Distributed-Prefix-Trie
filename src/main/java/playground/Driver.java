package playground;

import data.PrefixTrie;
import data.Util;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        Util trieUtil = new Util();
        PrefixTrie trie = trieUtil.retrieveTrie();
        trie.insertPrefix("Ryan Schachte");

        List<String> results = trie.prefixDfs("r");

    }
}
