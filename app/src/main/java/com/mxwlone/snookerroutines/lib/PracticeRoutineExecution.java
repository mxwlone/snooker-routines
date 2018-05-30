package com.mxwlone.snookerroutines.lib;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PracticeRoutineExecution extends SugarRecord {
    private int practiceRoutineId;
    private Date date;
    private String results;

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

    public void addResult(int index, int result) {
        try {
            JSONObject jsonResults = new JSONObject(this.results);
            jsonResults.put(Integer.toString(index), result);
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public JSONObject getResults() {
        try {
            return new JSONObject(this.results);
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage());
        }

        return new JSONObject();
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

    // TODO calculate average result of JSON results
//    public float getAverageResult() {
//        float averageResult = 0f;
//
//        if (results.size() == 0) {
//            return averageResult;
//        }
//
//        for (int result : results) {
//            averageResult += result;
//        }
//
//        return averageResult / results.size();
//    }
}
