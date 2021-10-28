package seedu.duke.logic.commands;


import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.food.Food;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds a Food item to the FoodBank.
 */
public class AddFoodBankCommand extends Command {
    public static final String MESSAGE_SUCCESS = "A food item has been added to the food bank:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_FOOD_BANK,
            COMMAND_PREFIX_CALORIES
    };

    private static Logger logger = Logger.getLogger(AddFoodBankCommand.class.getName());

    private Food food;

    public AddFoodBankCommand(String description, int calories) {
        this.food = new Food(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (food.getCalories() < 0) {
            logger.log(Level.FINE, "Food calorie is invalid");
            return new CommandResult(CommandMessages.MESSAGE_INVALID_FOOD_CALORIES);
        }
        assert food.getCalories() >= 0 : "Food calorie is valid";
        try {
            super.foodBank.addItem(this.food);
            logger.log(Level.FINE, "Food is successfully added to food bank");
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.food.toStringWithoutDateAndTime()));
        } catch (DuplicateItemInBankException e) {
            return new CommandResult(String.format(CommandMessages.MESSAGE_FOOD_ALREADY_EXISTS_IN_BANK,
                    this.food.getName()));
        }
    }
}
