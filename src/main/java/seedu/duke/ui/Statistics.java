package seedu.duke.ui;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/* A class that manage the statistics of the calories*/
public class Statistics {
    public static final String FULL_BLOCK = "█";
    public static final String HALF_BLOCK = "▌";
    public static final String MESSAGE_CALORIE_GAIN = "Your calorie gained from food is: %d";
    public static final String MESSAGE_CALORIE_LOST = "Your calorie lost from exercise is: %d";
    public static final String MESSAGE_CALORIE_NET = "Your net calorie intake is: %d";
    public static final String MESSAGE_CALORIE_GOAL = "Your calorie goal is: %d";
    public static final String MESSAGE_CALORIE_EXACT = "You have reached your calorie goal exactly. Good job!";
    public static final String MESSAGE_CALORIE_LESS_THAN = "You are %s cal away from your goal";
    public static final String MESSAGE_CALORIE_MORE_THAN = "You have exceeded your calorie goal by %s cal ";
    public static final String OVERVIEW_HEADER = "-*WEEKLY OVERVIEW*-\n"
            + "Hi %s, this is your calorie summary for the week.\n";
    public static final String FOOD_HEADER = "Food:\n"
            + "You have consumed %1$s cal this week from %2$s to %3$s.";
    public static final String EXERCISE_HEADER = "Exercise:\n"
            + "You have burnt %1$s cal this week from %2$s to %3$s.";
    public static final String FOOD_GRAPH_HEADER = "Calorie gained from food (Daily)\n%s";
    public static final String EXERCISE_GRAPH_HEADER = "Calorie burnt from exercise (Daily)\n%s";
    public static final String MESSAGE_CAUTION = "\n** Net calories = Food consumed - Exercise output - "
            + "your basal metabolic rate, where \n"
            + "your basal metabolic rate is a factor of your age, gender, "
            + "height and weight retrieved from your profile.\n"
            + "All calculations are done in calories.";

    public static final String GRAPH_BUILDER = "%1$s   %2$s    %3$s";
    public static final int MAX_DATE_OFFSET = 6;
    public static final String MESSAGE_NET_CALORIES_INTRO = "Daily net calories**:\n";
    public static final String MESSAGE_SUPPER_COUNT_INTRO = "Number of supper meals this week: %s";
    public static final int MAX_BAR_LENGTH = 30;
    public static final int EMPTY_CALORIES = 0;
    public static final String MESSAGE_DAILY_OVERVIEW = "This is your calorie overview for today:\n";


    private FoodList foodItems;
    private ExerciseList exerciseItems;
    private Profile profile;

    public Statistics(FoodList foodItems, ExerciseList exerciseItems, Profile profile) {
        this.foodItems = foodItems;
        this.exerciseItems = exerciseItems;
        this.profile = profile;
    }

    private static Logger logger = Logger.getLogger(Statistics.class.getName());
    private LocalDate date;

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

        //TODO:move this 4 lines to getToday overview
        int netCalories = calculateNetCalories(foodCalories, exerciseCalories);
        return new String[]{String.format(MESSAGE_CALORIE_GAIN, foodCalories),
                String.format(MESSAGE_CALORIE_LOST, exerciseCalories),
                String.format(MESSAGE_CALORIE_NET, netCalories),
                String.format(MESSAGE_CALORIE_GOAL, calorieGoal),
                printCaloriesMessage(netCalories, calorieGoal)};
    }

    private int calculateNetCalories(int foodCalories, int exerciseCalories) {
        try {
            return this.profile.calculateNetCalories(foodCalories, exerciseCalories);
        } catch (InvalidCharacteristicException e) {
            return 0;
        }
    }

    public String getCurrentDayOverview() {
        int foodCalories = foodItems.getTotalCaloriesWithDate(date);
        int exerciseCalories = exerciseItems.getTotalCaloriesWithDate(date);
        int calorieGoal = profile.getProfileCalorieGoal().getCalorieGoal();
        logger.log(Level.FINE, String.valueOf(calorieGoal));
        String[] messages = getCaloriesReport(exerciseCalories, foodCalories, calorieGoal);
        StringBuilder currentDayOverview = new StringBuilder(MESSAGE_DAILY_OVERVIEW);
        for (String message : messages) {
            currentDayOverview.append(message).append(Ui.LS);
        }
        return currentDayOverview.toString().trim();
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

    /**
     * Set the date to current date.
     * Date to be updated upon calling the overview command.
     */
    private void setLocalDate() {
        date = LocalDateTime.now().toLocalDate();
    }

    private LocalDate dateOffset(int offset) {
        return date.minusDays(offset);
    }

    private ArrayList<Integer> getDailyFoodCalories() {
        ArrayList<Integer> dailyFoodCalories = new ArrayList<>();
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(6)));
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(5)));
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(4)));
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(3)));
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(2)));
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(1)));// need to convert to stats chart
        dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date));
        return dailyFoodCalories;
    }

    private String getGraph(ArrayList<Integer> dailyCalories) {
        int maxCalories = Collections.max(dailyCalories);
        StringBuilder graph = new StringBuilder();
        int dateOffset = MAX_DATE_OFFSET;

        for (int calories : dailyCalories) {
            String progressBar = "";
            int numberOfBars;
            numberOfBars = (int) Math.round(((double) calories / maxCalories) * MAX_BAR_LENGTH);
            assert numberOfBars <= MAX_BAR_LENGTH : "30 is the max progress bar limit";
            for (int i = 0; i < numberOfBars; i++) {
                progressBar = progressBar + FULL_BLOCK;
            }
            String formattedDate = getFormatDate(dateOffset);
            graph.append(String.format(GRAPH_BUILDER, formattedDate, progressBar, calories)).append(Ui.LS);
            dateOffset--;
        }
        return graph.toString();
    }

    private String getFormatDate(int dateOffset) {
        String formattedDate = dateOffset(dateOffset).format(DateTimeFormatter.ofPattern("dd-MMM"));
        return formattedDate;
    }

    private ArrayList<Integer> getDailyExerciseCalories() {
        ArrayList<Integer> dailyExerciseCalories = new ArrayList<>();
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(6)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(5)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(4)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(3)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(2)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(1)));
        dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date));
        return dailyExerciseCalories;
    }

    private int getTotalWeeklyCalories(ArrayList<Integer> getCalories) {
        return getCalories.stream().mapToInt(i -> i).sum();
    }

    private String getSupperCountMessage() {
        int supperCount = foodItems.getSupperCount();
        return String.format(MESSAGE_SUPPER_COUNT_INTRO, supperCount);
    }

    private String getNetCaloriesMessage() {
        ArrayList<Integer> dailyExerciseCalories = getDailyExerciseCalories();
        ArrayList<Integer> dailyFoodCalories = getDailyFoodCalories();
        StringBuilder netCaloriesMessage = new StringBuilder(MESSAGE_NET_CALORIES_INTRO);
        for (int i = 0; i <= MAX_DATE_OFFSET; i++) {
            int exerciseCalories = dailyExerciseCalories.get(i) == null ? 0 : dailyExerciseCalories.get(i);
            int foodCalories = dailyFoodCalories.get(i) == null ? 0 : dailyFoodCalories.get(i);
            int netCalories = getNetCalories(foodCalories, exerciseCalories);
            String formattedDate = getFormatDate(MAX_DATE_OFFSET - i);
            netCaloriesMessage.append(formattedDate + " :   " + netCalories).append(Ui.LS);
        }
        return netCaloriesMessage.toString();
    }

    private int getNetCalories(int foodCalories, int exerciseCalories) {
        try {
            return profile.calculateNetCalories(foodCalories, exerciseCalories);
        } catch (InvalidCharacteristicException e) {
            return EMPTY_CALORIES;
        }
    }

    /**
     * An overview on user calorie intake and calorie burnt.
     *
     * @return String that contains summary of calories for the week.
     */
    public String overviewSummary() {
        setLocalDate(); // need ensure that the date is on the time of query
        StringBuilder overviewSummary = new StringBuilder();
        overviewSummary.append(String.format(OVERVIEW_HEADER, profile.getProfileName().getName())).append(Ui.LS)
                .append(String.format(FOOD_HEADER, getTotalWeeklyCalories(getDailyFoodCalories()),
                        getFormatDate(6), getFormatDate(0))).append(Ui.LS)
                .append(String.format(FOOD_GRAPH_HEADER, getGraph(getDailyFoodCalories()))).append(Ui.LS)
                .append(String.format(EXERCISE_HEADER, getTotalWeeklyCalories(getDailyExerciseCalories()),
                        getFormatDate(6), getFormatDate(0))).append(Ui.LS)
                .append(String.format(EXERCISE_GRAPH_HEADER, getGraph(getDailyExerciseCalories()))).append(Ui.LS)
                .append(getNetCaloriesMessage()).append(Ui.LS)
                .append(getSupperCountMessage()).append(Ui.LS)
                .append(MESSAGE_CAUTION).append(Ui.LS)
                .append(Ui.DIVIDER).append(Ui.LS)
                .append(getCurrentDayOverview());
        return overviewSummary.toString().trim();
    }


}
