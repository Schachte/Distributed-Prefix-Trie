package com.schachte.prefixservice.api;

import com.schachte.prefixservice.model.SearchPhrase;

import java.util.Map;

public interface RedisRepository {
    void add(SearchPhrase phrase);

    void delete(String id);

    void delete(int id);

    Map<Object, Object> findAllPhrases();
}
