package com.mxwlone.snookerroutines.lib;

import com.mxwlone.snookerroutines.util.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PracticeRoutines {

/* Template
            new PracticeRoutine(
                    "",
                    Style.,
                    Difficulty.,
                    new Tag[] {},
                    "",
                    "",
                    ""
            ),
*/

    //region PracticeRoutine definitions
    private static PracticeRoutine[] practiceRoutines = {
            // region BEGINNER

            // endregion
            // region EASY
            new PracticeRoutine(
                    "Black all the time",
                    Difficulty.EASY,
                    Style.DISCRETE,
                    10,
                    new Tag[] { Tag.POTTING },
                    "Set up a simple shot on the black: less than half ball angle and " +
                            "bridge on the table. Mark the spot of the white and play the shot. " +
                            "Repeat the exact same shot %d times.\n" +
                            "Play the next set from the other side of the table.",
                    "Add one point for each potted ball",
                    ""
            ),
            new PracticeRoutine(
                    "Pink all the time",
                    Difficulty.EASY,
                    Style.DISCRETE,
                    10,
                    new Tag[] { Tag.POTTING },
                    "Set up a simple shot on the pink: less than half ball angle and " +
                            "bridge on the table. Mark the spot of the white and play the shot. " +
                            "Repeat the exact same shot %d times.\n" +
                            "Play the next set from the other side of the table.",
                    "Add one point for each potted ball",
                    ""
            ),
            new PracticeRoutine(
                    "Blue all the time",
                    Difficulty.EASY,
                    Style.DISCRETE,
                    10,
                    new Tag[] { Tag.POTTING },
                    "Set up a simple shot on the blue: less than half ball angle and " +
                            "bridge on the table. Mark the spot of the white and play the shot. " +
                            "Repeat the exact same shot %d times.\n" +
                            "Play the next set from the other side of the table and vary the " +
                            "angle and distance.",
                    "Add one point for each potted ball",
                    ""
            ),
            new PracticeRoutine(
                    "Ten reds without cushion",
                    Difficulty.EASY,
                    Style.CONTINUOUS,
                    Integer.MAX_VALUE,
                    new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
                    "Pot the reds until you miss or hit the cushion. Respot all ten " +
                            "reds after the last red was potted.",
                    "Add one point for each potted ball",
                    ""
            ),
            // endregion
            // region MEDIUM
            new PracticeRoutine(
                    "Perfect stop shot",
                    Difficulty.MEDIUM,
                    Style.DISCRETE,
                    5,
                    new Tag[] { Tag.POSITIONAL_PLAY },
                    "Set up a straight shot on the red into the middle pocket. " +
                            "Mark the spot of the white and play a stop shot on the red. Repeat " +
                            "the exact same shot %d times. The white must stop exactly where it " +
                            "hits the red.\nPlay the next set from the other side of the table " +
                            "and vary the distance between the white and the red.",
                    "Add one point for each successful stop shot pot",
                    ""
            ),
            new PracticeRoutine(
                    "Colors",
                    Difficulty.MEDIUM,
                    Style.DISCRETE,
                    27,
                    new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
                    "Pot the colours in order until you miss or pot the black.",
                    "Sum up the values of the potted colors",
                    ""
            ),
            // endregion
            // region HARD
            new PracticeRoutine(
                    "Colors all the time",
                    Difficulty.HARD,
                    Style.CONTINUOUS,
                    Integer.MAX_VALUE,
                    new Tag[] { Tag.POTTING, Tag.POSITIONAL_PLAY },
                    "Pot the colors in order until you miss. After potting the black, " +
                            "respot all colors and continue the break with the yellow on.",
                    "Sum up the values of the potted colors",
                    ""
            ),
            // endregion
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
