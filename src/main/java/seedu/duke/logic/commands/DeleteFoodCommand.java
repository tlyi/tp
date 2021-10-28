package seedu.duke.logic.commands;

import seedu.duke.data.item.food.Food;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:"
            + CommandMessages.INDENTED_LS + "%1$s";
    public static final String MESSAGE_FOOD_CLEAR = "All food items have been removed.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_FOOD,
            COMMAND_PREFIX_DATE,
            COMMAND_PREFIX_TIME,
    };


    private final int itemIndex;
    private final LocalDate date;
    private final LocalTime time;
    private boolean isClear = false;

    private static final Logger logger = Logger.getLogger(DeleteFoodCommand.class.getName());

    public DeleteFoodCommand(int itemIndex, LocalDate date, LocalTime time) {
        this.itemIndex = itemIndex;
        this.date = date;
        this.time = time;
    }

    public DeleteFoodCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing food list");
            super.foodItems.clearList();
            assert foodItems.getSize() == 0 : "The size of the food list should be 0 after clear";
            return new CommandResult(MESSAGE_FOOD_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an object only";
        if (super.foodItems.getSize() == 0) {
            logger.log(Level.FINE, "Food list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FOOD_LIST);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Food deletedFood;
            deletedFood = super.foodItems.deleteItem(this.itemIndex, this.date, this.time);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    deletedFood.toStringWithDate()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid food item index.");
            return new CommandResult(String.format(CommandMessages.MESSAGE_FOOD_NOT_FOUND,
                    this.itemIndex + 1,
                    this.date.format(CommandMessages.DATE_FORMATTER),
                    this.time.format(CommandMessages.TIME_FORMATTER)));
        }
    }
}
