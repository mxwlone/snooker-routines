package com.mxwlone.snookerroutines.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.mxwlone.snookerroutines.R;
import com.mxwlone.snookerroutines.lib.PracticeRoutine;
import com.mxwlone.snookerroutines.lib.PracticeRoutineExecution;
import com.mxwlone.snookerroutines.lib.PracticeRoutines;
import com.mxwlone.snookerroutines.lib.Tag;
import com.mxwlone.snookerroutines.lib.TrainingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TrainingSetActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private TrainingSet trainingSet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_set);

        Intent intent = getIntent();
        trainingSet = (TrainingSet) intent.getSerializableExtra(MainActivity.TRAINING_SET);
        trainingSet.setActive(true);

        String trainingSetName = trainingSet.toString();
        Log.d(TAG, String.format("Training set name: %s", trainingSetName));

        // TODO delete the next lines
        PracticeRoutineExecution.deleteAll(PracticeRoutineExecution.class);
        Log.d(TAG, String.format("Deleted all %s table entries",
                PracticeRoutineExecution.class.getSimpleName()));

        Toolbar toolbar = setupToolbar();

        setupSpinner(trainingSet, toolbar);
    }

    public void onFinishButtonClick(View view) {
        // show dialog which verifies the wish to finish the training set
        trainingSet.setActive(false);

        // ORM tests
//        PracticeRoutineExecution.deleteAll(PracticeRoutineExecution.class);
//        PracticeRoutineExecution practiceRoutineExecution = new PracticeRoutineExecution(PracticeRoutines.getIdOfPracticeRoutine(PracticeRoutines.getAll().get(0)));
//        practiceRoutineExecution.addResult(10);
//        practiceRoutineExecution.save();

        Iterator<PracticeRoutineExecution> practiceRoutineExecutions = PracticeRoutineExecution.findAll(PracticeRoutineExecution.class);

        for (; practiceRoutineExecutions.hasNext(); ) {
            PracticeRoutineExecution practiceRoutineExecution = practiceRoutineExecutions.next();
//            Log.d(TAG, String.format("NAME: %s", practiceRoutineExecution.getPracticeRoutine().toString()));
            Log.d(TAG, String.format("ID: %d", practiceRoutineExecution.getPracticeRoutineId()));
            Log.d(TAG, String.format("DATE: %s", practiceRoutineExecution.getDate().toString()));
            Log.d(TAG, String.format("RESULTS: %s", practiceRoutineExecution.getResults().toString()));
        }

//        List<PracticeRoutineExecution> practiceRoutineExecution1 = PracticeRoutineExecution.find(PracticeRoutineExecution.class, "1 = 1");
//        Log.d("NAME", practiceRoutineExecution1.get(0).getPracticeRoutine().toString());
//        Log.d("DATE", practiceRoutineExecution1.get(0).getDate().toString());
//        Log.d("RESULTS", practiceRoutineExecution1.get(0).getResults().toString());

        finish();
    }

    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return toolbar;
    }

    private void setupSpinner(final TrainingSet trainingSet, Toolbar toolbar) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> practiceRoutinesList = new ArrayList<>();

        for (PracticeRoutine practiceRoutine : trainingSet.getPracticeRoutines()) {
            practiceRoutinesList.add(practiceRoutine.toString());
        }

        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                practiceRoutinesList.toArray(new String[0])));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int practiceRoutineId = trainingSet.getPracticeRoutineIds().get(position);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position, practiceRoutineId))
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    public static class PlaceholderFragment extends Fragment {

        private PracticeRoutineExecution practiceRoutineExecution;

        private final String TAG = this.getClass().getSimpleName();
        private static final String INDEX = "index";
        private static final String PRACTICE_ROUTINE_ID = "practice_routine_id";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int index, int practiceRoutineId) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(INDEX, index);
            args.putInt(PRACTICE_ROUTINE_ID, practiceRoutineId);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.activity_practice_routine, container, false);

            Bundle args = getArguments();
            final int index = args.getInt(INDEX);
            final PracticeRoutine practiceRoutine = PracticeRoutines.getById(args.getInt(PRACTICE_ROUTINE_ID));

            if (practiceRoutine == null) {
                return rootView;
            }

            setUpGui(rootView, practiceRoutine);

            practiceRoutineExecution = new PracticeRoutineExecution(practiceRoutine);

            return rootView;
        }

        private void setUpGui(View rootView, PracticeRoutine practiceRoutine) {
            ((TextView) rootView.findViewById(R.id.textViewPracticeRoutineName)).setText(practiceRoutine.toString());
            ((TextView) rootView.findViewById(R.id.textViewPracticeRoutineDescription)).setText(practiceRoutine.getDescription());

            LinearLayout linearLayoutTagArray = rootView.findViewById(R.id.linearLayoutTagArray);
            for (Tag tag : practiceRoutine.getTags()) {
                TextView t = new TextView(getContext());
                t.setText(tag.toString());
                linearLayoutTagArray.addView(t);
            }

            setUpResultInputs(rootView);
        }

        private void setUpResultInputs(View rootView) {
            EditText[] editTextResults = new EditText[] {
                    rootView.findViewById(R.id.editTextResult1),
                    rootView.findViewById(R.id.editTextResult2),
                    rootView.findViewById(R.id.editTextResult3),
                    rootView.findViewById(R.id.editTextResult4),
                    rootView.findViewById(R.id.editTextResult5),
            };

            for (final EditText editText : editTextResults) {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String result = editText.getText().toString();
                        int resultIndex = Integer.parseInt(editText.getTag().toString());
                        Log.d(TAG, String.format("Result field: %d", resultIndex));

                        if (TextUtils.isDigitsOnly(result) && !result.isEmpty()) {
                            Log.d(TAG, String.format("EditText Result: %s", result));
                            practiceRoutineExecution.addResult(resultIndex, Integer.parseInt(result));
                            practiceRoutineExecution.save();
                        } else if (result.isEmpty()) {
                            practiceRoutineExecution.removeResult(resultIndex);

                            if (practiceRoutineExecution.isEmpty()) {
                                practiceRoutineExecution.delete();
                            } else {
                                practiceRoutineExecution.save();
                            }
                        }
                    }
                });
            }
        }
    }
}
