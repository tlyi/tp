package seedu.duke.logic.commands;

import seedu.duke.data.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the ExerciseBank.
 */
public class DeleteExerciseBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_EXERCISE_BANK + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the exercise bank list";
    public static final String MESSAGE_SUCCESS = "An exercise item has been deleted from the exercise bank:"
            + CommandMessages.INDENTED_LS + "%s"
            + CommandMessages.LS + "Number of exercise item(s) left in the exercise bank: %2$d";
    private static final String MESSAGE_EXERCISE_CLEAR = "All exercise items in the exercise bank have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_EXERCISE_BANK};


    private static Logger logger = Logger.getLogger(DeleteExerciseBankCommand.class.getName());

    private final int itemIndex;
    private boolean isClear = false;

    public DeleteExerciseBankCommand(int itemIndex) {
        this.itemIndex = itemIndex; //-2
    }

    public DeleteExerciseBankCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing exercise bank");
            super.exerciseBank.clearList();
            return new CommandResult(MESSAGE_EXERCISE_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an item only";
        if (super.exerciseBank.getSize() == 0) {
            logger.log(Level.FINE, "Exercise bank is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_BANK);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Exercise deletedExercise;
            deletedExercise = (Exercise) super.exerciseBank.deleteItem(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    deletedExercise.toStringWithoutDateAndTime(),
                    super.exerciseBank.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise item index.");
            if (super.exerciseBank.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseBank.getSize()));
        }
    }
}
