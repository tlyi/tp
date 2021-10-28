package seedu.duke.logic.commands;


import seedu.duke.data.item.Item;
import seedu.duke.data.item.exceptions.DuplicateItemInBankException;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xingjie99
/**
 * Represents the command that when executed, edits an item in the Exercise Bank.
 */
public class EditExerciseBankCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Item number %d in the exercise bank has been changed to:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE_BANK,
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_CALORIES
    };


    private static Logger logger = Logger.getLogger(EditExerciseBankCommand.class.getName());

    private final int itemIndex;
    private final String newName;
    private final int newCalories;

    public EditExerciseBankCommand(int itemIndex, String newName, int newCalories) {
        this.itemIndex = itemIndex;
        this.newName = newName;
        this.newCalories = newCalories;
    }

    @Override
    public CommandResult execute() {
        if (super.exerciseBank.getSize() == 0) {
            logger.log(Level.FINE, "Exercise bank list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_EXERCISE_BANK);
        }
        try {
            Item item = super.exerciseBank.getItem(this.itemIndex);
            if (!this.newName.equals(NULL_STRING)) {
                super.exerciseBank.checkNoDuplicateItemName(this.newName);
                item.setName(this.newName);
            }
            if (this.newCalories != NULL_CALORIES) {
                if (this.newCalories <= 0) {
                    logger.log(Level.FINE, "Exercise calorie is invalid");
                    return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
                }
                item.setCalories(this.newCalories);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex + 1,
                    super.exerciseBank.getItem(this.itemIndex).toStringWithoutDateAndTime()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid exercise bank item index.");
            if (super.exerciseBank.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseBank.getSize()));
        } catch (DuplicateItemInBankException e) {
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_EXERCISE_ALREADY_EXISTS_IN_BANK, this.newName));
        }
    }

}
