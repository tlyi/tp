package seedu.duke.logic.commands;

import seedu.duke.data.item.food.Food;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodBank.
 */
public class DeleteFoodBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD_BANK + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the food bank list";
    public static final String MESSAGE_SUCCESS = "An food item has been deleted from the food bank:"
            + CommandMessages.INDENTED_LS + "%s"
            + CommandMessages.LS + "Number of food item(s) left in the food bank: %2$d";
    private static final String MESSAGE_FOOD_CLEAR = "All food items in the food bank have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_FOOD_BANK};


    private static Logger logger = Logger.getLogger(DeleteFoodBankCommand.class.getName());

    private final int itemIndex;
    private boolean isClear = false;

    public DeleteFoodBankCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public DeleteFoodBankCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing food bank");
            super.foodBank.clearList();
            return new CommandResult(MESSAGE_FOOD_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an item only";
        if (super.foodBank.getSize() == 0) {
            logger.log(Level.FINE, "food bank is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FOOD_BANK);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Food deletedFood;
            deletedFood = (Food) super.foodBank.deleteItem(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedFood, super.foodBank.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid food item index.");
            if (super.foodBank.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.foodBank.getSize()));
        }
    }
}
