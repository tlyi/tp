package seedu.duke.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the ExerciseBank.
 */
public class ViewExerciseBankCommand extends Command {
    public static final String MESSAGE_SUCCESS = "You have %1$d exercise(s) in your exercise bank:"
            + CommandMessages.LS + "%2$s";

    private static Logger logger = Logger.getLogger("ViewExerciseBankCommand");

    @Override
    public CommandResult execute() {
        if (super.exerciseBank.getSize() == 0) {
            logger.log(Level.FINE, "Exercise bank is empty");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_BANK);
        }
        assert exerciseBank.getSize() > 0 : "Exercise bank is not empty";
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.exerciseBank.getSize(),
                super.exerciseBank.convertToString()));
    }
}
