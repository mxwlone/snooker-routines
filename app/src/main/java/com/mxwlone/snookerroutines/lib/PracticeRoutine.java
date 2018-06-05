package com.mxwlone.snookerroutines.lib;

import java.io.Serializable;

public final class PracticeRoutine implements Serializable {

    private String imageUrl;
    private String name;
    private String description;
    private String scoring;
    private Difficulty difficulty;
    private Style style;
    private Tag[] tags;

    PracticeRoutine(String name, Difficulty difficulty, Style style, Tag[] tags, String description,
                    String scoring, String imageUrl) {
        this.name = name;
        this.difficulty = difficulty;
        this.style = style;
        this.description = description;
        this.scoring = scoring;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getScoring() {
        return scoring;
    }

    public Style getStyle() {
        return style;
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
