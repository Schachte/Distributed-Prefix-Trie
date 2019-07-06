package data;

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
        this.trie = initializeOrLoad();
    }

    public PrefixTrie initializeOrLoad() {
        if (trie == null) {
            trie = new PrefixTrie();
        }
        return trie;
    }

    public PrefixTrie retrieveTrie() {
        return trie;
    }

    public PrefixTrie load(String inputFile) throws FileNotFoundException {
        PrefixTrie trie = initializeOrLoad();
        Scanner sc = new Scanner(new File(inputFile));

        while (sc.hasNextLine()) {
            String currString = sc.next();
            trie.insertPrefix(currString);
        }

        return trie;
    }

}
