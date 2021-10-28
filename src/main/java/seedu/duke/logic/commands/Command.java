package seedu.duke.logic.commands;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;

import java.time.LocalDate;

/**
 * Abstract class used to represent executable Commands.
 * All Commands can be executed to return a CommandResult.
 */
public abstract class Command {
    public static final String COMMAND_PREFIX_DELIMITER = "/";
    public static final String COMMAND_PREFIX_EXERCISE = "e";
    public static final String COMMAND_PREFIX_UPCOMING_EXERCISE = "u";
    public static final String COMMAND_PREFIX_RECURRING = "r";
    public static final String COMMAND_PREFIX_FOOD = "f";
    public static final String COMMAND_PREFIX_EXERCISE_BANK = "ebank";
    public static final String COMMAND_PREFIX_FOOD_BANK = "fbank";
    public static final String COMMAND_PREFIX_CALORIES = "c";
    public static final String COMMAND_PREFIX_NAME = "n";
    public static final String COMMAND_PREFIX_HEIGHT = "h";
    public static final String COMMAND_PREFIX_WEIGHT = "w";
    public static final String COMMAND_PREFIX_GOAL = "g";
    public static final String COMMAND_PREFIX_DATE = "d";
    public static final String COMMAND_PREFIX_TIME = "t";
    public static final String COMMAND_PREFIX_GENDER = "s";
    public static final String COMMAND_PREFIX_AGE = "a";
    public static final String COMMAND_PREFIX_ACTIVITY_FACTOR = "x";
    public static final String COMMAND_PREFIX_START_DATE = ":";
    public static final String COMMAND_PREFIX_END_DATE = "-";
    public static final String COMMAND_PREFIX_DAY_OF_THE_WEEK = "@";
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_VIEW = "view";
    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String COMMAND_WORD_BMI = "bmi";
    public static final String COMMAND_WORD_PROFILE = "profile";
    public static final String COMMAND_WORD_DELETE_ALL = "all";
    public static final double NULL_DOUBLE = Double.MIN_VALUE;
    public static final int NULL_INT = Integer.MIN_VALUE;
    public static final char NULL_CHAR = '|';
    public static final int NULL_CALORIES = -1;
    public static final String NULL_STRING = "";
    public static final LocalDate NULL_DATE = LocalDate.MIN;


    protected Profile profile;
    protected ExerciseList exerciseItems;
    protected FoodList foodItems;
    protected FutureExerciseList futureExerciseItems;
    protected ItemBank exerciseBank;
    protected ItemBank foodBank;


    /**
     * Returns the appropriate CommandResult after execution of the command.
     * Each child class that inherits this class represents an executable command and will have its own implementation
     * of this method specific to its functionality.
     */
    public abstract CommandResult execute();

    /**
     * Provides the necessary data structures for the command to operate on.
     */
    public void setData(DataManager dataManager) {
        this.profile = dataManager.getProfile();
        this.exerciseItems = dataManager.getFilteredExerciseItems();
        this.foodItems = dataManager.getFilteredFoodItems();
        this.futureExerciseItems = dataManager.getFutureExerciseItems();
        this.exerciseBank = dataManager.getExerciseBank();
        this.foodBank = dataManager.getFoodBank();
        assert profile != null : "Profile supplied to command should not be null";
        assert exerciseItems != null : "Exercise items supplied to command should not be null";
        assert foodItems != null : "Food items supplied to command should not be null";
        assert futureExerciseItems != null : "Future exercise items supplied to command should not be null";
        assert exerciseBank != null : "Exercise bank supplied to command should not be null";
        assert foodBank != null : "Food bank supplied to command should not be null";
    }


    /**
     * Returns true if the command requires the profile storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if profile storage file is to be rewritten after execution of the command
     */
    public static boolean requiresProfileStorageRewrite(Command command) {
        return command instanceof ProfileUpdateCommand;
    }

    /**
     * Returns true if the command requires the exercise list storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if exercise list storage file is to be rewritten after execution of the command
     */
    public static boolean requiresExerciseListStorageRewrite(Command command) {
        return command instanceof AddExerciseCommand
                || command instanceof DeleteExerciseCommand;
    }

    /**
     * Returns true if the command requires the food list storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if food list storage file is to be rewritten after execution of the command
     */
    public static boolean requiresFoodListStorageRewrite(Command command) {
        return command instanceof AddFoodCommand
                || command instanceof DeleteFoodCommand;
    }

    /**
     * Returns true if the command requires the future exercise list storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if future exercise list storage file is to be rewritten after execution of the command
     */
    public static boolean requiresFutureExerciseListStorageRewrite(Command command) {
        return command instanceof AddFutureExerciseCommand
                || command instanceof DeleteFutureExerciseCommand
                || command instanceof AddRecurringExerciseCommand
                || command instanceof EditFutureExerciseCommand;
    }

    /**
     * Returns true if the command requires the food bank storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if food bank storage file is to be rewritten after execution of the command
     */
    public static boolean requiresFoodBankStorageRewrite(Command command) {
        return command instanceof AddFoodBankCommand
                || command instanceof DeleteFoodBankCommand
                || command instanceof EditFoodBankCommand;
    }

    /**
     * Returns true if the command requires the exercise bank storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if food bank storage file is to be rewritten after execution of the command
     */
    public static boolean requiresExerciseBankStorageRewrite(Command command) {
        return command instanceof AddExerciseBankCommand
                || command instanceof DeleteExerciseBankCommand
                || command instanceof EditExerciseBankCommand;
    }


}
