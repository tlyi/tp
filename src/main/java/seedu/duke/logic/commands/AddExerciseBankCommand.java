package seedu.duke.logic.commands;

import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds an Exercise item to the ExerciseBank.
 */
public class AddExerciseBankCommand extends Command {
    public static final String MESSAGE_SUCCESS = "An exercise item has been added to the exercise bank:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE_BANK,
            COMMAND_PREFIX_CALORIES
    };

    private static Logger logger = Logger.getLogger(AddExerciseBankCommand.class.getName());

    private Exercise exercise;

    public AddExerciseBankCommand(String description, int calories) {
        this.exercise = new Exercise(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (exercise.getCalories() <= 0) {
            logger.log(Level.FINE, "Exercise calorie is invalid");
            return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        try {
            super.exerciseBank.addItem(this.exercise);
            logger.log(Level.FINE, "Exercise is successfully added to exercise bank");
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.exercise));
        } catch (DuplicateItemInBankException e) {
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_EXERCISE_ALREADY_EXISTS_IN_BANK, this.exercise.getName()));
        }
    }
}
