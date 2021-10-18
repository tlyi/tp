package seedu.duke.ui;

import java.util.logging.Level;
import java.util.logging.Logger;


/* A class that manage the statistics of the calories*/
public class Statistics {
    public static final String MESSAGE_CALORIE_GAIN = "Your calorie gained from food is: %d";
    public static final String MESSAGE_CALORIE_LOST = "Your calorie lost from exercise is: %d";
    public static final String MESSAGE_CALORIE_NET = "Your net calorie intake is: %d";
    public static final String MESSAGE_CALORIE_GOAL = "Your calorie goal is: %d";
    public static final int REVERSE_APPEND = 1;
    public static final String EMPTY = "";
    public static final String MESSAGE_CALORIE_EXACT = "You have reached your calorie goal exactly. Good job!";
    public static final String MESSAGE_CALORIE_LESS_THAN = "You are %s cal away from your goal";
    public static final String MESSAGE_CALORIE_MORE_THAN = "You have exceeded your calorie goal by %s cal ";

    public Statistics() {
    }

    private static Logger logger = Logger.getLogger(Statistics.class.getName());

    /**
     * Calculate netCalories and format exerciseCalories, foodCalories, calorieGoal
     * into strings.
     *
     * @param exerciseCalories is the total calories lost by exercising
     * @param foodCalories     is the total calories gained by consuming food
     * @param calorieGoal      is the goal set by the user
     * @return formatted strings.
     */
    public String[] getCaloriesReport(int exerciseCalories, int foodCalories, int calorieGoal) {

        int netCalories = foodCalories - exerciseCalories;
        int remainingCalories = calorieGoal - netCalories;
        return new String[]{String.format(MESSAGE_CALORIE_GAIN, foodCalories),
                String.format(MESSAGE_CALORIE_LOST, exerciseCalories),
                String.format(MESSAGE_CALORIE_NET, netCalories),
                String.format(MESSAGE_CALORIE_GOAL, calorieGoal),
                printCaloriesMessage(netCalories, calorieGoal)};
    }

    public String formatMessage(String... messages) {
        StringBuilder content = new StringBuilder(EMPTY);
        for (String message : messages) {
            content.append(message).append(Ui.LS);
        }
        content.setLength(content.length() - REVERSE_APPEND);
        return content.toString().stripTrailing();
    }

    public String printCaloriesMessage(int netCalories, int calorieGoal) {
        logger.log(Level.FINE, "preparing calories message");
        int calorieDifference = calorieGoal - netCalories;
        String message;
        if (calorieGoal > netCalories) {
            message = String.format(MESSAGE_CALORIE_LESS_THAN, calorieDifference);
        } else if (calorieGoal < netCalories) {
            message = String.format(MESSAGE_CALORIE_MORE_THAN, -calorieDifference);
        } else {
            assert calorieDifference == 0 : "calorieDifference should be 0";

            message = String.format(MESSAGE_CALORIE_EXACT);
        }
        return message;
    }

}