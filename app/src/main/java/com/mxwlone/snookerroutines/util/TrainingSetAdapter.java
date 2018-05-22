package com.mxwlone.snookerroutines.util;

import android.content.Context;
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
            TextView textViewTrainingSetExecutions = v.findViewById(R.id.textViewTrainingSetExecutions);
            LinearLayout linearLayoutTrainingSetPracticeRoutineArray =
                    v.findViewById(R.id.linearLayoutTrainingSetPracticeRoutineArray);
            LinearLayout linearLayoutTrainingSetTagArray =
                    v.findViewById(R.id.linearLayoutTrainingSetTagArray);

            if (textViewTrainingSetName != null) {
                textViewTrainingSetName.setText(trainingSet.toString());
            }
//            if (textViewTrainingSetExecutions != null) {
//                textViewTrainingSetExecutions.setText(Integer.toString(trainingSet.getExecutions()));
//            }
            if (linearLayoutTrainingSetPracticeRoutineArray != null) {
                if (linearLayoutTrainingSetPracticeRoutineArray.getChildCount() == 0) {
                    for (PracticeRoutine practiceRoutine : trainingSet.getPracticeRoutines()) {
                        TextView t = new TextView(getContext());
                        t.setText(practiceRoutine.toString());
                        linearLayoutTrainingSetPracticeRoutineArray.addView(t);
                    }
                }
            }
            if (linearLayoutTrainingSetTagArray != null) {
                // todo count tags of all practice routines and sort by number of appearances
                // minimal working example: show tags of first practice routine
                if (linearLayoutTrainingSetTagArray.getChildCount() == 0) {
                    for (Tag tag : trainingSet.getPracticeRoutines().get(0).getTags()) {
                        TextView t = new TextView(getContext());
                        t.setText(tag.toString());
                        linearLayoutTrainingSetTagArray.addView(t);
                    }
                }
            }
        }

        return v;
    }
}
