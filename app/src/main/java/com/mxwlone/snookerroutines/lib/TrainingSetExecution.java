package com.mxwlone.snookerroutines.lib;

import java.util.Date;

final class TrainingSetExecution {
    private TrainingSet trainingSet;
    private Date date;
    private int executions;
    private boolean isActive;

    public TrainingSetExecution(TrainingSet trainingSet, Date date, boolean isActive) {
        this.trainingSet = trainingSet;
        this.date = date;
        this.isActive = isActive;
    }

    public int getExecutions() {
        return executions;
    }

    public boolean isActive() {
        return isActive;
    }

    public TrainingSet getTrainingSet() {
        return trainingSet;
    }

    public Date getDate() {
        return date;
    }
}
