package com.mxwlone.snookerroutines.lib;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PracticeRoutineExecution extends SugarRecord {
    private int practiceRoutineId;
    private Date date;
    private List<Integer> results = new ArrayList<>();

    public PracticeRoutineExecution() {
    }

    public PracticeRoutineExecution(int practiceRoutineId) {
        this.date = new Date();
        this.practiceRoutineId = practiceRoutineId;
    }

    public int getPracticeRoutineId() {
        return practiceRoutineId;
    }

    public PracticeRoutine getPracticeRoutine() {
        return PracticeRoutines.getById(practiceRoutineId);
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
