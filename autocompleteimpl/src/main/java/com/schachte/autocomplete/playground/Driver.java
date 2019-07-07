package com.schachte.autocomplete.playground;

import com.schachte.autocomplete.data.PrefixTrie;
import com.schachte.autocomplete.data.Util;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        Util trieUtil = new Util();
        PrefixTrie trie = trieUtil.retrieveTrie();
        trie.insertPrefix("sd");
        List<String> results = trie.prefixDfs("r");
    }
}
