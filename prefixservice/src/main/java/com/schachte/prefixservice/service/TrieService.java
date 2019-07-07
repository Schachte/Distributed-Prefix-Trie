package com.schachte.prefixservice.service;

import com.schachte.autocomplete.data.PrefixTrie;
import com.schachte.prefixservice.api.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TrieService {

    private PrefixTrie trie;

    public TrieService() {
        this.trie = new PrefixTrie();
    }

    public int getRootChildren() {
        return trie.getRoot().getChildCount();
    }

    public void addPrefix(String prefix) {
        trie.insertPrefix(prefix);
    }
}
