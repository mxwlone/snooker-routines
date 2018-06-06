package com.mxwlone.snookerroutines.lib;

import android.util.Log;

import com.mxwlone.snookerroutines.util.MapUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TrainingSet implements Serializable {
    private static final String TAG = PracticeRoutine.class.getSimpleName();

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

    public List<Integer> getPracticeRoutineIds() {
        return this.practiceRoutineIds;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Tag> getSortedTagList() {
        List<Tag> sortedTagList;
        Map<Tag, Integer> tagCountMap = new HashMap<>();

        for (PracticeRoutine practiceRoutine : getPracticeRoutines()) {
            Tag[] practiceRoutineTagArray = practiceRoutine.getTags();

            for (Tag tag : practiceRoutineTagArray) {
                if (!tagCountMap.containsKey(tag)) {
                    tagCountMap.put(tag, 1);
                } else {
                    tagCountMap.put(tag, tagCountMap.get(tag) + 1);
                }
            }
        }

        tagCountMap = MapUtil.sortByValue(tagCountMap);
        sortedTagList = new ArrayList<>(tagCountMap.keySet());
        Collections.reverse(sortedTagList);

        return sortedTagList;
    }
}

