package seedu.duke.logic.commands;

import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;
import seedu.duke.data.item.food.Food;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds a Food item to the FoodList.
 */
public class AddFoodCommand extends Command {
    public static final String MESSAGE_SUCCESS = "A food item has been added:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_FOOD,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE,
            COMMAND_PREFIX_TIME
    };


    private Logger logger = Logger.getLogger(AddFoodCommand.class.getName());
    private final String description;
    private int calories;
    private final LocalDateTime dateTime;
    private final boolean isCaloriesFromBank;

    public AddFoodCommand(String description, int calories, LocalDateTime dateTime, boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.dateTime = dateTime;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    @Override
    public CommandResult execute() {
        final Food food;

        if (isCaloriesFromBank) {
            try {
                this.calories = super.foodBank.findCalorie(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_INVALID_FOOD_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories < 0) {
                logger.log(Level.FINE, "Detected negative food calorie");
                return new CommandResult(CommandMessages.MESSAGE_INVALID_FOOD_CALORIES);
            }
        }

        food = new Food(this.description, this.calories, this.dateTime);
        super.foodItems.addItem(food);
        assert foodItems.getSize() > 0 : "The size of the food list should at least larger than 0";
        logger.log(Level.FINE, "New food item has been added to the food list");
        return new CommandResult(String.format(MESSAGE_SUCCESS, food.toStringWithDate()));

    }
}

