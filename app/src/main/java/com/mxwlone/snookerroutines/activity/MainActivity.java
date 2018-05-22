package com.mxwlone.snookerroutines.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mxwlone.snookerroutines.R;
import com.mxwlone.snookerroutines.lib.TrainingSet;
import com.mxwlone.snookerroutines.util.TrainingSetAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String TRAINING_SET = "training_set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArrayList<TrainingSet> dummyTrainingSets = new ArrayList<>();
        dummyTrainingSets.addAll(TrainingSet.getDummyTrainingSets());
        final ArrayAdapter trainingSetsArrayAdapter = new TrainingSetAdapter(this,
                R.layout.list_item_training_set, dummyTrainingSets);

        if (TrainingSet.getNumberOfInstances() == 0) {
            setContentView(R.layout.activity_main_blank);
            return;
        }

        setContentView(R.layout.activity_main);

        ListView listViewTrainingSets = findViewById(R.id.listViewTrainingSets);
        listViewTrainingSets.setAdapter(trainingSetsArrayAdapter);

        listViewTrainingSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onTrainingSetClickListener(i, dummyTrainingSets);
            }
        });
    }

    private void onTrainingSetClickListener(int i, ArrayList<TrainingSet> trainingSets) {
//        Toast.makeText(MainActivity.this, trainingSets.get(i).toString() , Toast.LENGTH_SHORT).show();
//        List<PracticeRoutine> practiceRoutines = trainingSets.get(i).getPracticeRoutines();
//        for (PracticeRoutine practiceRoutine : practiceRoutines) {
//            Log.d("ONCLICK", practiceRoutine.toString());
//        }

        Intent intent = new Intent(this, TrainingSetActivity.class);
        intent.putExtra(TRAINING_SET, trainingSets.get(i));
        startActivity(intent);
    }
}
