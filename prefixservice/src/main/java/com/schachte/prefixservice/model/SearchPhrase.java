package com.schachte.prefixservice.model;

import java.time.LocalDate;

/**
 * Represents the prefix phrase that will be persisted within the cache
 */
public class SearchPhrase {
    private String id;
    private String phrase;
    private LocalDate dateCreated;

    public SearchPhrase(String phrase) {
        this.phrase = phrase;
        this.dateCreated = LocalDate.now();
        this.id = "22";
    }

    public String getId() {
        return id;
    }

    public String getPhrase() {
        return phrase;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}
