package com.mxwlone.snookerroutines.lib;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public final class TrainingSet implements Serializable {
    private static int numberOfInstances;

    private String name;
    private int executions = 0;
    private List<PracticeRoutine> practiceRoutines;
    private boolean isActive = false;

    public TrainingSet(String name, List<PracticeRoutine> practiceRoutines) {
        numberOfInstances++;
        this.name = name;
        this.practiceRoutines = practiceRoutines;
    }

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    public List<PracticeRoutine> getPracticeRoutines() {
        return practiceRoutines;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    public int getExecutions() {
        return executions;
    }

    public void setExecutions(int executions) {
        this.executions = executions;
    }

    public static List<TrainingSet> getDummyTrainingSets() {
        TrainingSet firstDummyTrainingSet = new TrainingSet
                ("Warm up", PracticeRoutines.getAllByDifficulty(Difficulty.BEGINNER));
        TrainingSet secondDummyTrainingSet = new TrainingSet
                ("Training", PracticeRoutines.getAllByDifficulty(Difficulty.EASY));

        return Arrays.asList(firstDummyTrainingSet, secondDummyTrainingSet);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
