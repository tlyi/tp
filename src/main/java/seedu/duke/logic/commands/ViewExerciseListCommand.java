package seedu.duke.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the ExerciseList.
 */
public class ViewExerciseListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Here is a summary of all the exercises you have done "
            + "in the past week:" + CommandMessages.LS + "%1$s";


    private static Logger logger = Logger.getLogger("ViewExerciseCommand");

    @Override
    public CommandResult execute() {
        if (super.exerciseItems.getSize() == 0) {
            logger.log(Level.FINE, "Exercise list is empty");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_LIST);
        }
        assert exerciseItems.getSize() > 0 : "Exercise list is not empty";
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.exerciseItems.convertToString()));
    }
}
