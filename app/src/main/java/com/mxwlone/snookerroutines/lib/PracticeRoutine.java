package com.mxwlone.snookerroutines.lib;

import java.io.Serializable;

public final class PracticeRoutine implements Serializable {

    private String imageUrl;
    private String name;
    private String description;
    private Difficulty difficulty;
    private Tag[] tags;

    PracticeRoutine(String name, Difficulty difficulty, Tag[] tags, String description, String imageUrl) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Tag[] getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    Difficulty getDifficulty() {
        return difficulty;
    }

    public String toString() {
        return getName();
    }
}
