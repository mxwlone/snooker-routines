package com.mxwlone.snookerroutines.lib;

import android.util.Log;

import java.io.Serializable;

public final class PracticeRoutine implements Serializable {
    private static final String TAG = PracticeRoutine.class.getSimpleName();

    private String imageUrl;
    private String name;
    private String description;
    private String scoringDescription;
    private Difficulty difficulty;
    private Style style;
    private int maxScore;
    private Tag[] tags;

    PracticeRoutine(String name, Difficulty difficulty, Style style, int maxScore, Tag[] tags,
                    String description, String scoringDescription, String imageUrl) {
        this.name = name;
        this.difficulty = difficulty;
        this.style = style;
        this.maxScore = maxScore;
        this.description = style == Style.DISCRETE ?
                String.format(description, maxScore) : description;
        this.scoringDescription = scoringDescription;
        this.imageUrl = imageUrl;
        this.tags = tags;

        if (style == Style.CONTINUOUS && maxScore != Integer.MAX_VALUE) {
            Log.w(TAG, "Max score set for non-discrete practice routine!");
        }
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getScoringDescription() {
        return scoringDescription;
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
