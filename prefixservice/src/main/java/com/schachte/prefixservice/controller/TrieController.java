package com.schachte.prefixservice.controller;

import com.schachte.prefixservice.model.SearchPhrase;
import com.schachte.prefixservice.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/trie")
public class TrieController {

    @Autowired
    private TrieService trieService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/demo/{prefix}")
    public String addThing(@PathVariable("prefix") String prefix) {
//        redisTemplate.opsForSet().add("ryan", new SearchPhrase("tester"));
        redisTemplate.opsForZSet().add("sortedset", "testdata1", 1);
        redisTemplate.opsForZSet().add("sortedset", "testdata2", 1);
        redisTemplate.opsForZSet().add("sortedset", "testdata3", 12);
        redisTemplate.opsForZSet().add("sortedset", "testdata4", 15);
        redisTemplate.opsForZSet().add("sortedset", "testdata5", 11);
        redisTemplate.opsForZSet().add("sortedset", "testdata6", 10);
        redisTemplate.opsForZSet().add("sortedset", "testdata7", 96);
        redisTemplate.opsForZSet().add("sortedset", "testdata8", 97);
        redisTemplate.opsForZSet().add("sortedset", "testdata9", 98);
        redisTemplate.opsForZSet().add("sortedset", "testdata10", 99);
        redisTemplate.opsForZSet().add("sortedset", "testdata11", 100);
        redisTemplate.opsForZSet().incrementScore("sortedset", "testdata11", 1);

        Set<ZSetOperations.TypedTuple<String>> retrievalSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("sortedset", 0, Double.POSITIVE_INFINITY, 0, 3);
        retrievalSet.stream().forEach(tuple ->
                redisTemplate.opsForZSet().incrementScore("sortedset", tuple.getValue(), 1)
        );
        Set<ZSetOperations.TypedTuple<String>> postIncrScore = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("sortedset", 0, Double.POSITIVE_INFINITY, 0, 3);
        return "success - added: " + prefix;
    }

    @PostMapping("/prefix")
    public String createProduct(@RequestBody SearchPhrase phrase) {
        trieService.addPrefix(phrase.getPhrase());
        return "Product created successfully!";
    }

    @GetMapping("/size")
    public int getTreeSize() {
        return trieService.getRootChildren();
    }
}
