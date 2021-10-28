package seedu.duke.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the FoodBank.
 */
public class ViewFoodBankCommand extends Command {
    public static final String MESSAGE_SUCCESS = "You have %1$d food(s) in your food bank:"
            + CommandMessages.LS + "%2$s";

    private static Logger logger = Logger.getLogger("ViewFoodBankCommand");

    @Override
    public CommandResult execute() {
        if (super.foodBank.getSize() == 0) {
            logger.log(Level.FINE, "Food bank is empty");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FOOD_BANK);
        }
        assert foodBank.getSize() > 0 : "Food bank is not empty";
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.foodBank.getSize(),
                super.foodBank.convertToString()));
    }
}
