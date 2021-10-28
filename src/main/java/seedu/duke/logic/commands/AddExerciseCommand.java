package seedu.duke.logic.commands;


import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;
import seedu.duke.data.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds an Exercise item to the ExerciseList.
 */
public class AddExerciseCommand extends Command {
    public static final String MESSAGE_SUCCESS = "An exercise item has been added:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE
    };


    private static Logger logger = Logger.getLogger(AddExerciseCommand.class.getName());

    private final String description;
    private int calories;
    private final LocalDate date;
    private final boolean isCaloriesFromBank;

    public AddExerciseCommand(String description, int calories, LocalDate date, boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.date = date;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    @Override
    public CommandResult execute() {
        final Exercise exercise;

        if (isCaloriesFromBank) {
            try {
                this.calories = super.exerciseBank.findCalorie(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories <= 0) {
                logger.log(Level.FINE, "Exercise calorie is invalid");
                return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
            }

        }

        exercise = new Exercise(this.description, this.calories, this.date);
        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        super.exerciseItems.addItem(exercise);
        logger.log(Level.FINE, "Exercise is successfully added");
        return new CommandResult(String.format(MESSAGE_SUCCESS, exercise.toStringWithDate()));
    }
}
