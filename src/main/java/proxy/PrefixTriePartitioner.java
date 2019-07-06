package proxy;

import java.util.*;

import static java.lang.Character.toUpperCase;

/**
 * Utility to create a partitioning scheme to proxy requests to the proper prefix
 * trie when load balancing distributed prefix phrases across a cluster.
 *
 * Example use-case:
 * User will request a particular prefix phrase of: "hello world". The input phrase
 * is going to get routed to an in-memory cache. If there is a cache miss, the load balancer
 * will load balance to this partitioner instance. The partitioner will do a
 * range-query on the input prefix to route to the proper prefix trie to insert into.
 *
 * Each IP address maps to a running node with an open-facing prefix trie API to insert into.
 */
public class PrefixTriePartitioner {

    /**
     * Encapsulate basic metadata about trie node in cluster
     * to sorted against ranges when query is made.
     */
    private static class TrieNode {
        protected char startRange;
        protected String ipAddress;

        public TrieNode(char startRange, String ipAddress) {
            this.startRange = startRange;
            this.ipAddress = ipAddress;
        }
    }

    /**
     * Sorted hashmap that will allow us to do range-based lookups based on input
     * prefix phrases. This routing table will be used to map to ranges of nodes within
     * the trie cluster.
     */
    protected TreeMap<Character, TrieNode> nodeMappings = new TreeMap<>();

    /**
     * Inputs a range-based entry into the map to enable proxying
     * the request to the proper node.
     *
     * @param range     START,END (ie, A,G)
     * @param ipAddress 192.168.0.1
     */
    public void addNodeMapping(String range, String ipAddress) {
        TrieNode node = new TrieNode(range.charAt(0), ipAddress);
        nodeMappings.put(range.charAt(0), node);
    }

    /**
     * Overarching goal is to redirect the user request to the proper IP address
     * of the prefix trie containing the data of the requested prefix phrase.
     *
     * @param prefixReq The requesting prefix phrase from the client
     * @return IP address matching the range of the prefix
     */
    public String retrieveNodeMapping(String prefixReq) {
        char key = toUpperCase(prefixReq.charAt(0));

        Map.Entry<Character, TrieNode> e = nodeMappings.floorEntry(key);
        if (e != null && e.getValue() == null) {
            e = nodeMappings.lowerEntry(key);
        }
        return e == null ? null : e.getValue().ipAddress;
    }

}
