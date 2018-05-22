package com.mxwlone.snookerroutines.lib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

final class PracticeRoutineExecution {
    private PracticeRoutine practiceRoutine;
    private Date date;
    private List<Integer> results = new ArrayList<>();
    private float averageResult;

    public PracticeRoutineExecution(PracticeRoutine practiceRoutine) {
        this.date = new Date();
        this.practiceRoutine = practiceRoutine;
    }

    public PracticeRoutine getPracticeRoutine() {
        return practiceRoutine;
    }

    public Date getDate() {
        return date;
    }

    public void addResult(int result) {
        results.add(result);
    }

    public List<Integer> getResults() {
        return results;
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

    public float getAverageResult() {
        float averageResult = 0f;

        if (results.size() == 0) {
            return averageResult;
        }

        for (int result : results) {
            averageResult += result;
        }

        return averageResult / results.size();
    }
}
