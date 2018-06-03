package com.mxwlone.snookerroutines.lib;

import com.mxwlone.snookerroutines.util.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PracticeRoutines {

/* Template
            new PracticeRoutine(
                    "",
                    Difficulty.BEGINNER,
                    new Tag[] {},
                    "",
                    ""
            ),
*/

    //region PracticeRoutine definitions
    private static PracticeRoutine[] practiceRoutines = {
            new PracticeRoutine(
                    "Colors",
                    Difficulty.BEGINNER,
                    new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
                    "Pot the colours in order. After black, return to yellow and continue the break.",
                    ""
            ),
            new PracticeRoutine(
                    "Ten reds without cushion",
                    Difficulty.EASY,
                    new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
                    "Pot the reds without touching the cushion.",
                    ""
            ),
            new PracticeRoutine(
                    "Black all the time",
                    Difficulty.EASY,
                    new Tag[] { Tag.POTTING },
                    "Pot the black and put it back on its spot. " +
                            "Don't stop the exercise until you miss the black. \n" +
                            "You must always start this exercise with the bridge on the cushion.",
                    ""
            )
    };
    //endregion

    public static List<PracticeRoutine> getAll() {
        return Arrays.asList(practiceRoutines);
    }

    public static PracticeRoutine getById(int id) {
        return getAll().get(id);
    }

    public static int getIdOfPracticeRoutine(PracticeRoutine practiceRoutine) {
        return getAll().indexOf(practiceRoutine);
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
        List<PracticeRoutine> allExercisesAsList = Arrays.asList(practiceRoutines);

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
