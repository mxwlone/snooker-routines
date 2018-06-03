package com.mxwlone.snookerroutines.lib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TrainingSet implements Serializable {
    private static int numberOfInstances;

    private String name;
    private int executions = 0;
    private List<Integer> practiceRoutineIds;
    private boolean active = false;

    public TrainingSet(String name, List<PracticeRoutine> practiceRoutines) {
        numberOfInstances++;
        this.name = name;

        this.practiceRoutineIds = new ArrayList<>();

        for (PracticeRoutine practiceRoutine : practiceRoutines) {
            this.practiceRoutineIds.add(PracticeRoutines.getIdOfPracticeRoutine(practiceRoutine));
        }
    }

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    public List<PracticeRoutine> getPracticeRoutines() {
        List<PracticeRoutine> practiceRoutines = new ArrayList<>();

        for (int practiceRoutineId : practiceRoutineIds) {
            practiceRoutines.add(PracticeRoutines.getById(practiceRoutineId));
        }

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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
