package seedu.duke.logic.commands;

import seedu.duke.data.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the ExerciseList.
 */
public class DeleteExerciseCommand extends Command {
    public static final String MESSAGE_SUCCESS = "An exercise item has been deleted:"
            + CommandMessages.INDENTED_LS + "%s";
    private static final String MESSAGE_EXERCISE_CLEAR = "All exercise items have been removed.";

    private static Logger logger = Logger.getLogger(DeleteExerciseCommand.class.getName());
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE,
            COMMAND_PREFIX_DATE
    };


    private final int itemIndex;
    private final LocalDate date;
    private boolean isClear = false;

    public DeleteExerciseCommand(int itemIndex, LocalDate date) {
        this.itemIndex = itemIndex;
        this.date = date;
    }

    public DeleteExerciseCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
        this.date = LocalDate.now();
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing exercise list");
            super.exerciseItems.clearList();
            return new CommandResult(MESSAGE_EXERCISE_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an item only";
        if (super.exerciseItems.getSize() == 0) {
            logger.log(Level.FINE, "Exercise list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_LIST);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Exercise deletedExercise;
            deletedExercise = super.exerciseItems.deleteItem(this.itemIndex, this.date);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    deletedExercise.toStringWithDate()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(CommandMessages.MESSAGE_EXERCISE_NOT_FOUND,
                    this.itemIndex + 1,
                    this.date.format(CommandMessages.DATE_FORMATTER)));
        }
    }
}
