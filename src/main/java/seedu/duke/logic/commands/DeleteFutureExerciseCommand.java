package seedu.duke.logic.commands;

import seedu.duke.data.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the Future ExerciseList.
 */
public class DeleteFutureExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_UPCOMING_EXERCISE + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the future exercise list";
    public static final String MESSAGE_SUCCESS = "An exercise item for the future has been deleted:"
            + CommandMessages.INDENTED_LS + "%s"
            + CommandMessages.LS + "Number of upcoming exercise(s) left: %2$d";
    private static final String MESSAGE_FUTURE_EXERCISE_CLEAR = "All future exercise items have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_UPCOMING_EXERCISE};


    private static Logger logger = Logger.getLogger(DeleteFutureExerciseCommand.class.getName());

    private final int itemIndex;
    private boolean isClear = false;

    public DeleteFutureExerciseCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public DeleteFutureExerciseCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing future exercise list");
            super.futureExerciseItems.clearList();
            return new CommandResult(MESSAGE_FUTURE_EXERCISE_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an item only";
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.FINE, "Future exercise list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FUTURE_EXERCISE_LIST);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Exercise deletedExercise;
            deletedExercise = super.futureExerciseItems.deleteItem(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedExercise.toStringWithDate(),
                    super.futureExerciseItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise item index.");
            if (super.futureExerciseItems.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS,
                    super.futureExerciseItems.getSize()));
        }
    }
}
