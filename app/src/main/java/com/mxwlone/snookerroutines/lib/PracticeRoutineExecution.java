package com.mxwlone.snookerroutines.lib;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PracticeRoutineExecution extends SugarRecord implements Serializable {
    private static final String TAG = PracticeRoutineExecution.class.getSimpleName();

    private int practiceRoutineId;
    private Date date;
    private String results = new JSONObject().toString();

    public PracticeRoutineExecution() {
    }

    public PracticeRoutineExecution(PracticeRoutine practiceRoutine) {
        this.practiceRoutineId = PracticeRoutines.getIdOfPracticeRoutine(practiceRoutine);
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void addResult(int index, int result) {
        Log.d(TAG, String.format("::: addResult(%d,%d)", index, result));
        try {
            JSONObject jsonResults = new JSONObject(this.results);

            if (isEmpty()) {
                setDate(new Date());
            }

            jsonResults.put(Integer.toString(index), result);
            this.results = jsonResults.toString();
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage());
        }

        save();
    }

    public void removeResult(int index) {
        Log.d(TAG, String.format("::: removeResult(%d)", index));
        try {
            JSONObject jsonResults = new JSONObject(this.results);
            jsonResults.remove(Integer.toString(index));
            this.results = jsonResults.toString();
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage());
        }

        if (isEmpty()) {
            delete();
        } else {
            save();
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
        JSONObject jsonResults;
        try {
            jsonResults = new JSONObject(this.results);
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage());
            return true;
        }

        return jsonResults.length() == 0;
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
