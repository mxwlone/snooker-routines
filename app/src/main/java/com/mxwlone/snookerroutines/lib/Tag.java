package com.mxwlone.snookerroutines.lib;

public enum Tag {
    POTTING,
    SAFETY,
    POSITIONAL_PLAY,
    BREAK_BUILDING;

    @Override
    public String toString() {
        return super.toString().replaceAll("_", " ").toLowerCase();
    }
}