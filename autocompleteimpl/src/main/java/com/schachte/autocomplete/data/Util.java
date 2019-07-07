package com.schachte.autocomplete.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Handles the serialization and deserialization of a given trie.
 * This becomes useful when you want to write the contents of a trie and then
 * rebuild the trie from disk into memory.
 */
public class Util {

    private PrefixTrie trie;

    public Util() {
        this.trie = new PrefixTrie();
    }

    /**
     * Grabs current instance of the trie loaded into memory
     */
    public PrefixTrie retrieveTrie() {
        return trie;
    }

    /**
     * Loads strings from a file into the trie. This is useful when rebuilding
     * the trie from disk and back into memory. This will be executed when the tree is torn
     * down and the top K values are loaded into the trie from disk.
     *
     * @param inputFile representing path of textual com.schachte.autocomplete.data to inject into the tree
     * @return PrefixTrie instance of a new tree
     * @throws FileNotFoundException
     */
    public PrefixTrie loadNew(String inputFile) throws FileNotFoundException {
        PrefixTrie newTrie = new PrefixTrie();
        Scanner sc = new Scanner(new File(inputFile));

        while (sc.hasNextLine()) {
            String currString = sc.next();
            newTrie.insertPrefix(currString);
        }

        trie.killTrie();
        trie = newTrie;

        return trie;
    }

    /**
     * Loads strings from a file into the trie. This is useful when inserting new values
     * into an already existing tree that you want to still maintain.
     *
     * @param inputFile representing path of textual com.schachte.autocomplete.data to inject into the tree
     * @return PrefixTrie that is the current instance
     * @throws FileNotFoundException
     */
    public PrefixTrie loadCurrent(String inputFile) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(inputFile));

        while (sc.hasNextLine()) {
            String currString = sc.next();
            trie.insertPrefix(currString);
        }

        return trie;
    }

}
