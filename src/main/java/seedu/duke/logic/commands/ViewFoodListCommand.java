package seedu.duke.logic.commands;

/**
 * Represents the command that when executed, lists all the items in the FoodList.
 */
public class ViewFoodListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + QUOTATION;
    public static final String MESSAGE_SUCCESS = "You have consumed %1$d food item(s):"
            + LS + "%2$s";


    @Override
    public CommandResult execute() {
        if (super.foodItems.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_FOOD_LIST);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.foodItems.getSize(),
                super.foodItems.convertToString()));
    }
}