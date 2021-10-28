package seedu.duke.data.item.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FutureExerciseList extends ExerciseList {

    private static final int ONE_DAY = 1;
    private static final int ONE_WEEK = 7;

    /**
     * Deletes and exercise item from the future exercise list.
     *
     * @param index Index of the exercise to be deleted.
     * @return Exercise object removed.
     */
    public Exercise deleteItem(int index) {
        return (Exercise) internalItems.remove(index);
    }

    /**
     * Converts the entire future exercise list to string format for printing purpose.
     *
     * @return The future exercise list in a single string.
     */
    @Override
    public String convertToString() {
        StringBuilder futureExerciseListToString = new StringBuilder();

        for (int i = 0; i < internalItems.size(); i++) {
            futureExerciseListToString
                    .append(TAB)
                    .append(i + 1)
                    .append(". ")
                    .append(internalItems.get(i))
                    .append(" (")
                    .append(getDayOfWeek(internalItems.get(i).getDate()))
                    .append(" ")
                    .append(internalItems.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .append(")")
                    .append(LS);
        }
        return futureExerciseListToString.toString().stripTrailing();
    }

    /**
     * Adds all recurring exercises between two dates into the FutureExerciseList.
     *
     * @param description The name description of the recurring exercise
     * @param calories The calorie burnt per each exercise
     * @param startDate The starting date of the recurring exercise
     * @param endDate The end date of the recurring exercise
     * @param dayOfTheWeek The day(s) of the week for participating in the recurring exercise
     */
    public void addRecurringExercises(String description, int calories,
                                      LocalDate startDate, LocalDate endDate, ArrayList<Integer> dayOfTheWeek) {
        for (Integer day : dayOfTheWeek) {
            int dayOfReoccurrence = startDate.getDayOfWeek().getValue();
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
                if (dayOfReoccurrence == day) {
                    super.addItem(new Exercise(description, calories, currentDate));
                    currentDate = currentDate.plusDays(ONE_WEEK);
                } else {
                    currentDate = currentDate.plusDays(ONE_DAY);
                }
                dayOfReoccurrence = currentDate.getDayOfWeek().getValue();
            }
            super.sortList();
        }
    }
}
