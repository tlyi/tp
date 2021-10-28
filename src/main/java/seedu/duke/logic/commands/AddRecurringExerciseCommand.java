package seedu.duke.logic.commands;

import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xingjie99
/**
 * Represent the command that when executed, adds all recurring Exercise items to the FutureExerciseList.
 */
public class AddRecurringExerciseCommand extends Command {
    public static final String MESSAGE_INVALID_DATES = "Your start date %s is later than your end date %s";
    public static final String MESSAGE_INVALID_FUTURE_DATES = "Make sure that your start date (%s) "
            + "and end date (%s) are in the future";
    public static final String MESSAGE_NO_EXERCISE_ADDED = "Day(s) not present between %s and %s";
    public static final String MESSAGE_SUCCESS = "Recurring exercise item for the future has been added";
    private static final int ONE_WEEK = 7;
    private static final int ONE_DAY = 1;
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_RECURRING,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_START_DATE,
            COMMAND_PREFIX_END_DATE,
            COMMAND_PREFIX_DAY_OF_THE_WEEK
    };

    private final String description;
    private int calories;
    private boolean isCaloriesFromBank;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<Integer> dayOfTheWeek;

    private static Logger logger = Logger.getLogger(AddRecurringExerciseCommand.class.getName());

    public AddRecurringExerciseCommand(String description, int calories, LocalDate startDate,
                                       LocalDate endDate, ArrayList<Integer> dayOfTheWeek,
                                       boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfTheWeek = dayOfTheWeek;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    @Override
    public CommandResult execute() {
        if (this.startDate.isAfter(this.endDate)) {
            logger.log(Level.FINE, "Start date is after end date");
            return new CommandResult(String.format(MESSAGE_INVALID_DATES,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        }
        if (isCaloriesFromBank) {
            try {
                this.calories = super.exerciseBank.findCalorie(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories <= 0) {
                logger.log(Level.FINE, "Exercise calorie is invalid");
                return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
            }
        }
        assert this.endDate.isAfter(this.startDate) : "End date is after start date";
        if (!this.startDate.isAfter(LocalDate.now())) {
            logger.log(Level.FINE, "Recurring exercises are for future only");
            return new CommandResult(String.format(MESSAGE_INVALID_FUTURE_DATES,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        }
        assert this.startDate.isAfter(LocalDate.now()) : "Start and end dates are in the future";
        if (this.calories <= 0) {
            logger.log(Level.FINE, "Exercise calorie is invalid");
            return new CommandResult(CommandMessages.MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        assert this.calories > 0 : "Exercise calorie is valid";
        int numberOfFutureExercises = futureExerciseItems.getSize();
        futureExerciseItems.addRecurringExercises(this.description, this.calories,
                this.startDate, this.endDate, this.dayOfTheWeek);
        if (futureExerciseItems.getSize() == numberOfFutureExercises) {
            return new CommandResult(String.format(MESSAGE_NO_EXERCISE_ADDED,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        }

        logger.log(Level.FINE, "Recurring exercise is successfully added");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}



