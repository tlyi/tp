package seedu.duke.logic.commands;


import seedu.duke.data.item.Item;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xingjie99
/**
 * Represents the command that when executed, edits an item in the Future Exercise List.
 */
public class EditFutureExerciseCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Item number %d in upcoming exercises has been changed to:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_DATE = "The new date must be after today's date!";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_UPCOMING_EXERCISE,
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE,
    };

    private static Logger logger = Logger.getLogger(EditFutureExerciseCommand.class.getName());

    private final int itemIndex;
    private final String newName;
    private final int newCalories;
    private final LocalDate newDate;

    public EditFutureExerciseCommand(int itemIndex, String newName, int newCalories, LocalDate newDate) {
        this.itemIndex = itemIndex;
        this.newName = newName;
        this.newCalories = newCalories;
        this.newDate = newDate;
    }

    @Override
    public CommandResult execute() {
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.FINE, "Future exercise list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FUTURE_EXERCISE_LIST);
        }
        try {
            Item item = super.futureExerciseItems.getItem(this.itemIndex);
            if (!this.newName.equals(NULL_STRING)) {
                item.setName(this.newName);
            }
            if (this.newCalories != NULL_CALORIES) {
                if (this.newCalories <= 0) {
                    logger.log(Level.FINE, "Exercise calorie is invalid");
                    return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
                }
                item.setCalories(this.newCalories);
            }
            if (!this.newDate.equals(NULL_DATE)) {
                if (!this.newDate.isAfter(LocalDate.now())) {
                    return new CommandResult(MESSAGE_INVALID_DATE);
                }
                item.setDate(this.newDate);
            }
            super.futureExerciseItems.sortList();
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex + 1,
                    item.toStringWithDate()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise item index.");
            if (super.futureExerciseItems.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.futureExerciseItems.getSize()));
        }
    }
}
