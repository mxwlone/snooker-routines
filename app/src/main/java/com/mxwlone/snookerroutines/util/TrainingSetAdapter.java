package com.mxwlone.snookerroutines.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxwlone.snookerroutines.R;
import com.mxwlone.snookerroutines.lib.PracticeRoutine;
import com.mxwlone.snookerroutines.lib.Tag;
import com.mxwlone.snookerroutines.lib.TrainingSet;

import java.util.ArrayList;
import java.util.List;


public final class TrainingSetAdapter extends ArrayAdapter {

    private ArrayList<TrainingSet> trainingSets;

    public TrainingSetAdapter(@NonNull Context context, int textViewResourceId,
                              @NonNull ArrayList<TrainingSet> trainingSets) {
        super(context, textViewResourceId, trainingSets);
        this.trainingSets = trainingSets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_training_set, null);
        }

        TrainingSet trainingSet = trainingSets.get(position);

        if (trainingSet != null) {
            TextView textViewTrainingSetName = v.findViewById(R.id.textViewTrainingSetName);
//            TextView textViewTrainingSetExecutions = v.findViewById(R.id.textViewTrainingSetExecutions);
            LinearLayout linearLayoutTrainingSetPracticeRoutines =
                    v.findViewById(R.id.linearLayoutTrainingSetPracticeRoutines);
            LinearLayout linearLayoutTrainingSetTags =
                    v.findViewById(R.id.linearLayoutTrainingSetTags);

            if (textViewTrainingSetName != null) {
                textViewTrainingSetName.setText(trainingSet.toString());
            }
//            if (textViewTrainingSetExecutions != null) {
//                textViewTrainingSetExecutions.setText(Integer.toString(trainingSet.getExecutions()));
//            }
            if (linearLayoutTrainingSetPracticeRoutines != null) {
                if (linearLayoutTrainingSetPracticeRoutines.getChildCount() == 0) {
                    for (PracticeRoutine practiceRoutine : trainingSet.getPracticeRoutines()) {
                        TextView t = new TextView(getContext());
                        t.setText(practiceRoutine.toString());
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        llp.setMargins(0, 0, 20, 0);
                        t.setLayoutParams(llp);
                        linearLayoutTrainingSetPracticeRoutines.addView(t);
                    }
                }
            }
            if (linearLayoutTrainingSetTags != null) {
                if (linearLayoutTrainingSetTags.getChildCount() == 0) {
                    List<Tag> sortedTagList = trainingSet.getSortedTagList();
                    for (Tag tag : sortedTagList) {
                        TextView t = new TextView(getContext());
                        t.setText(tag.toString());
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        llp.setMargins(0, 0, 10, 0);
                        t.setLayoutParams(llp);
                        t.setPadding(10,2,10,2);
                        t.setBackgroundResource(R.color.colorPrimary);
                        t.setTextColor(Color.WHITE);
                        linearLayoutTrainingSetTags.addView(t);
                    }
                }
            }
        }

        return v;
    }
}
