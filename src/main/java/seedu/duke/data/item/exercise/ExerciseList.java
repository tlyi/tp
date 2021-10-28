package seedu.duke.data.item.exercise;

import seedu.duke.data.item.Item;
import seedu.duke.data.item.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ExerciseList extends ItemList {
    public static final String MESSAGE_EXERCISE_DONE_PER_DAY = "You have done %d exercise(s) on %s (%s):";
    public static final String MESSAGE_TOTAL_CALORIE_BURNT_PER_DAY = "Total calories burnt in the day: %d cal";
    public static final String MESSAGE_TOTAL_CALORIE_BURNT_PER_WEEK = "Total calorie burnt in this week: %d cal";
    public static final String MESSAGE_TOTAL_EXERCISE_DONE_PER_WEEK = "Total exercises done in this week: %d";

    /**
     * Default constructor for exercise list.
     */
    public ExerciseList() {
        internalItems = new ArrayList<>();
    }

    /**
     * Deletes a food item according to its index number, date and time.
     *
     * @param index The index of the exercise as shown in the view e/ command
     * @param date  The date of the exercise taken
     * @return The deleted exercise
     */
    public Exercise deleteItem(int index, LocalDate date) {
        Exercise deletedExercise = new Exercise("", 1, date);
        int actualIndex = getActualIndex(index, deletedExercise);
        deletedExercise = (Exercise) internalItems.remove(actualIndex);
        return deletedExercise;
    }

    /**
     * Converts the entire exercise list to string format for printing purpose.
     *
     * @return The exercise list in a single string
     */
    @Override
    public String convertToString() {
        StringBuilder exerciseListInString = extractExerciseListByEachDate();
        return exerciseListInString.toString().stripTrailing();
    }

    /**
     * Converts the food list of a specific date to string format for printing purpose.
     *
     * @param date The date for the food list
     * @return The food list of the specific date in a single string
     */
    public String convertToStringBySpecificDate(LocalDate date) {
        StringBuilder exerciseListInString = extractExerciseListBySpecificDate(date);
        return exerciseListInString.toString().stripTrailing();
    }

    /**
     * Sorts the exercise list in ascending format according to the date.
     */
    public void sortList() {
        this.internalItems.sort(Comparator.comparing(Item::getDate));
    }


    //====================Private methods=========================

    /**
     * Helper method used in deleteItem for exercise to get the
     * actual index from the entire exercise list of the exercise to delete.
     *
     * @param index           The index of the exercise as shown in the view e/ command
     * @param deletedExercise The exercise to delete
     * @return The actual index of the exercise in the entire exercise list
     */
    private int getActualIndex(int index, Item deletedExercise) {
        for (int i = 0; i < internalItems.size(); i++) {
            if (isListToQuery(deletedExercise, i)) {
                if (isExerciseToDelete(deletedExercise, i, index)) {
                    return i + index;
                }
                break;
            }
        }
        return -1;
    }

    /**
     * Helper boolean method used in getActualIndex to determine if the exercise is the exercise to delete.
     *
     * @param deletedExercise The exercise to delete
     * @param currentIndex    The current index of the entire exercise list
     * @param index           The exercise index to delete as shown in view e/
     * @return True if the current exercise is the exercise to delete, false otherwise
     */
    private boolean isExerciseToDelete(Item deletedExercise, int currentIndex, int index) {
        return internalItems.get(currentIndex + index).getDate().equals(deletedExercise.getDate());
    }

    /**
     * Helper method used in getActualIndex to determine if the current index points to the correct exercise position.
     *
     * @param deletedExercise The exercise to delete
     * @param currentIndex    The current index of the entire exerciselist
     * @return True if the current exercise has the same date and time period as the deletedItem, false otherwise
     */
    private boolean isListToQuery(Item deletedExercise, int currentIndex) {
        return internalItems.get(currentIndex).getDate().equals(deletedExercise.getDate());
    }

    /**
     * Helper function used in convertToString to extract exercises list
     * according to each date presented in the entire exercise list.
     *
     * @return String which contains exercise lists with different date
     */
    private StringBuilder extractExerciseListByEachDate() {
        StringBuilder exerciseListInString = new StringBuilder();
        for (int index = 0; index < internalItems.size(); index++) {
            LocalDate currentDate = internalItems.get(index).getDate();
            ExerciseList subList = new ExerciseList();
            while (index < internalItems.size() && currentDate.isEqual(internalItems.get(index).getDate())) {
                subList.addItem(internalItems.get(index++));
            }
            exerciseListInString.append(ITEM_LIST_DIVIDER).append(LS);
            convertItemCountToString(exerciseListInString,
                    subList.getSize(),
                    currentDate,
                    MESSAGE_EXERCISE_DONE_PER_DAY);
            for (int i = 1; i <= subList.getSize(); i++) {
                convertItemToString(exerciseListInString, i, subList.getItem(i - 1));
            }
            convertTotalCaloriesToString(
                    exerciseListInString,
                    this.getTotalCaloriesWithDate(currentDate),
                    MESSAGE_TOTAL_CALORIE_BURNT_PER_DAY);
            index--;
        }
        exerciseListInString.append(ITEM_LIST_DIVIDER).append(LS);
        exerciseListInString.append(String.format(MESSAGE_TOTAL_EXERCISE_DONE_PER_WEEK, getSize())).append(LS);
        exerciseListInString.append(String.format(MESSAGE_TOTAL_CALORIE_BURNT_PER_WEEK, getTotalCalories()));
        exerciseListInString.append(LS);
        return exerciseListInString;
    }

    /**
     * Helper method used in convertToStringBySpecificDate for extracting
     * exercise list which contains all the exercises done on the date.
     *
     * @param date The date to query all the exercises done
     * @return StringBuilder type string which contains an exercise list with the given date
     */
    private StringBuilder extractExerciseListBySpecificDate(LocalDate date) {
        StringBuilder exerciseListInString = new StringBuilder();
        ArrayList<Item> subList = (ArrayList<Item>) this.internalItems.stream()
                .filter(e -> e.getDate().isEqual(date))
                .collect(Collectors.toList());
        convertItemCountToString(exerciseListInString, subList.size(), date, MESSAGE_EXERCISE_DONE_PER_DAY);
        for (int i = 1; i <= subList.size(); i++) {
            convertItemToString(exerciseListInString, i, subList.get(i - 1));
        }
        convertTotalCaloriesToString(
                exerciseListInString,
                this.getTotalCaloriesWithDate(date),
                MESSAGE_TOTAL_CALORIE_BURNT_PER_DAY);
        return exerciseListInString;
    }
}
