package com.mxwlone.snookerroutines.lib;

import com.mxwlone.snookerroutines.util.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PracticeRoutines {

    private static PracticeRoutine TEMPLATE = new PracticeRoutine(
            "",
            Difficulty.BEGINNER,
            new Tag[] {},
            "",
            ""
    );

    //region PracticeRoutine definitions

    private static PracticeRoutine COLORS = new PracticeRoutine(
            "Colors",
            Difficulty.BEGINNER,
            new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
            "Pot the colours in order. After black, return to yellow and continue the break.",
            ""
    );

    private static PracticeRoutine TEN_REDS_WITHOUT_CUSHION = new PracticeRoutine(
            "Ten reds without cushion",
            Difficulty.EASY,
            new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
            "Pot the reds without touching the cushion.",
            ""
    );

    private static PracticeRoutine BLACK_ALL_THE_TIME = new PracticeRoutine(
            "Black all the time",
            Difficulty.EASY,
            new Tag[] { Tag.POTTING },
            "Pot the black and put it back on its spot. " +
                    "Don't stop the exercise until you miss the black. \n" +
                    "You must always start this exercise with the bridge on the cushion.",
            ""
    );

    //endregion

    private static PracticeRoutine[] allPracticeRoutines = {
            COLORS, TEN_REDS_WITHOUT_CUSHION, BLACK_ALL_THE_TIME
    };

    private static List<PracticeRoutine> getAll() {
        return Arrays.asList(allPracticeRoutines);
    }

    public static List<PracticeRoutine> getAllByDifficulty(final Difficulty difficulty) {
        List<PracticeRoutine> allPracticeRoutineList = getAll();

        return filterList(allPracticeRoutineList, new Predicate<PracticeRoutine>() {
            @Override
            public boolean test(PracticeRoutine e) {
                return e.getDifficulty().equals(difficulty);
            }
        });
    }

    public static List<PracticeRoutine> getAllByTag(final Tag tag) {
        List<PracticeRoutine> allPracticeRoutineList = getAll();

        return filterList(allPracticeRoutineList, new Predicate<PracticeRoutine>() {
            @Override
            public boolean test(PracticeRoutine e) {
                return Arrays.asList(e.getTags()).contains(tag);
            }
        });
    }

    public static List<PracticeRoutine> getAllByTagArray(final Tag[] tags) {
        List<PracticeRoutine> allExercisesAsList = Arrays.asList(allPracticeRoutines);

        return filterList(allExercisesAsList, new Predicate<PracticeRoutine>() {
            @Override
            public boolean test(PracticeRoutine e) {
                boolean hasTag = false;
                for (Tag tag:tags) {
                    hasTag = Arrays.asList(e.getTags()).contains(tag);

                    if (hasTag) {
                        break;
                    }
                }

                return hasTag;
            }
        });
    }


    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for (T element:list){
            if(predicate.test(element)){
                filtered.add(element);
            }
        }

        return filtered;
    }

}
