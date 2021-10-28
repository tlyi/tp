package seedu.duke.logic.commands;

import java.time.format.DateTimeFormatter;

/**
 * Contains all the general error messages that can be returned from Command classes.
 */
public class CommandMessages {
    //================ styling ================
    public static final String LS = System.lineSeparator();
    public static final String TAB = "\t";
    public static final String INDENTED_LS = CommandMessages.LS + TAB;
    public static final String QUOTATION = "\"";
    public static final String LONG_SPACE = "              ";
    public static final String BANK_SPACE = "          ";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    //================ item type prefixes ================
    public static final String MESSAGE_EXERCISE_LIST_FORMAT =
            Command.COMMAND_PREFIX_EXERCISE + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "exercise list";
    public static final String MESSAGE_FOOD_LIST_FORMAT =
            Command.COMMAND_PREFIX_FOOD + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "food list";
    public static final String MESSAGE_EXERCISE_BANK_FORMAT =
            Command.COMMAND_PREFIX_EXERCISE_BANK + Command.COMMAND_PREFIX_DELIMITER + BANK_SPACE + "exercise bank";
    public static final String MESSAGE_FOOD_BANK_FORMAT =
            Command.COMMAND_PREFIX_FOOD_BANK + Command.COMMAND_PREFIX_DELIMITER + BANK_SPACE + "food bank";
    public static final String MESSAGE_RECURRING_EXERCISE_FORMAT =
            Command.COMMAND_PREFIX_RECURRING + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "recurring exercise";

    //================ attribute prefixes and description ================
    public static final String MESSAGE_NAME_FORMAT =
            Command.COMMAND_PREFIX_NAME + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "name";
    public static final String MESSAGE_HEIGHT_FORMAT =
            Command.COMMAND_PREFIX_HEIGHT + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "height (cm)";
    public static final String MESSAGE_WEIGHT_FORMAT =
            Command.COMMAND_PREFIX_WEIGHT + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "weight (kg)";
    public static final String MESSAGE_AGE_FORMAT =
            Command.COMMAND_PREFIX_AGE + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "age";
    public static final String MESSAGE_GOAL_FORMAT =
            Command.COMMAND_PREFIX_GOAL + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "calorie goal";
    public static final String MESSAGE_GENDER_FORMAT =
            Command.COMMAND_PREFIX_GENDER + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "gender (M/F)";
    public static final String MESSAGE_ACTIVITY_FACTOR_FORMAT =
            Command.COMMAND_PREFIX_ACTIVITY_FACTOR + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE
                    + "activity factor (1: Sedentary - 5: Extra Active)";
    public static final String MESSAGE_ITEM_CALORIES_FORMAT =
            Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "calories";
    public static final String MESSAGE_UPCOMING_EXERCISE_LIST_FORMAT =
            Command.COMMAND_PREFIX_UPCOMING_EXERCISE + Command.COMMAND_PREFIX_DELIMITER
                    + LONG_SPACE + "upcoming exercise list";
    public static final String MESSAGE_ITEM_DATE_FORMAT =
            Command.COMMAND_PREFIX_DATE + Command.COMMAND_PREFIX_DELIMITER + LONG_SPACE + "date (DD-MM-YYYY)";


    //================ invalid attribute messages ================
    public static final String MESSAGE_INVALID_FOOD_CALORIES =
            "Food calories cannot be less than 0" + LS + "Try a positive value instead!";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES =
            "Exercise calories cannot be less than or equal to 0" + LS + "Try a positive value instead";
    public static final String MESSAGE_EMPTY_EXERCISE_LIST = "No exercise items yet in the past 7 days!";
    public static final String MESSAGE_EMPTY_FUTURE_EXERCISE_LIST = "No future exercise items yet!";
    public static final String MESSAGE_EMPTY_FOOD_LIST = "No food items yet in the past 7 days!";
    public static final String MESSAGE_EMPTY_EXERCISE_BANK = "No exercise items yet in the exercise bank!";
    public static final String MESSAGE_EMPTY_FOOD_BANK = "No food items yet in the food bank!";
    public static final String MESSAGE_ONLY_ONE_IN_LIST = "You have only 1 item in the list!";
    public static final String MESSAGE_LIST_OUT_OF_BOUNDS = "Please input a valid item number from 1 to %s";
    public static final String MESSAGE_FOOD_NOT_FOUND = "Food item with index %d, date %s and time %s "
            + "is not found in the food list!";
    public static final String MESSAGE_EXERCISE_NOT_FOUND = "Exercise item with index %d and date %s "
            + "is not found in the exercise list!";
    public static final String MESSAGE_INVALID_EXERCISE_NOT_IN_BANK = "%s was not found in the exercise bank! "
            + "Please specify the calories for this item using the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    public static final String MESSAGE_INVALID_FOOD_NOT_IN_BANK = "%s was not found in the food bank! "
            + "Please specify the calories for this item using the prefix "
            + QUOTATION + Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER + QUOTATION + "!";
    public static final String MESSAGE_FOOD_ALREADY_EXISTS_IN_BANK = "The food with name "
            + QUOTATION + "%s" + QUOTATION
            + " already exists in the food bank! (Names are case insensitive)"
            + LS + "Try using another name, or delete/edit the existing item first!";
    public static final String MESSAGE_EXERCISE_ALREADY_EXISTS_IN_BANK = "The exercise with name "
            + QUOTATION + "%s" + QUOTATION
            + " already exists in the exercise bank! (Names are case insensitive)"
            + LS + "Try using another name, or delete/edit the existing item first!";


    //================ command format suggestions ================
    //add command
    public static final String MESSAGE_ADD_COMMAND_INVALID_FORMAT =
            "Oops! Invalid format! Trying to add to your lists?" + LS
                    + "Type " + QUOTATION + Command.COMMAND_WORD_ADD + QUOTATION
                    + " followed by one of the following prefixes:" + LS + INDENTED_LS
                    + MESSAGE_EXERCISE_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_EXERCISE_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_RECURRING_EXERCISE_FORMAT + LS + LS
                    + "Type " + QUOTATION + HelpCommand.COMMAND_WORD + QUOTATION
                    + " if you need more details on how to add to each list!";
    //delete command
    public static final String MESSAGE_DELETE_COMMAND_INVALID_FORMAT =
            "Oops! Invalid format! Trying to delete from your lists?" + LS
                    + "Type " + QUOTATION + Command.COMMAND_WORD_DELETE + QUOTATION
                    + " followed by one of the following prefixes:" + LS + INDENTED_LS
                    + MESSAGE_EXERCISE_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_EXERCISE_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_UPCOMING_EXERCISE_LIST_FORMAT + LS + LS
                    + "Type " + QUOTATION + HelpCommand.COMMAND_WORD + QUOTATION
                    + " if you need more details on how to delete from each list!";
    //view command
    public static final String MESSAGE_VIEW_COMMAND_INVALID_FORMAT =
            "Oops! Invalid format! Trying to view your lists?" + LS
                    + "Type " + QUOTATION + Command.COMMAND_WORD_VIEW + QUOTATION
                    + " followed by one of the following prefixes:" + LS + INDENTED_LS
                    + MESSAGE_EXERCISE_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_LIST_FORMAT + INDENTED_LS
                    + MESSAGE_EXERCISE_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_UPCOMING_EXERCISE_LIST_FORMAT + LS + LS
                    + "E.G: view e/";
    //edit command
    public static final String MESSAGE_EDIT_COMMAND_INVALID_FORMAT =
            "Oops! Invalid format! Trying to edit your lists?" + LS
                    + "Type " + QUOTATION + Command.COMMAND_WORD_EDIT + QUOTATION
                    + " followed by one of the following prefixes:" + LS + INDENTED_LS
                    + MESSAGE_EXERCISE_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_FOOD_BANK_FORMAT + INDENTED_LS
                    + MESSAGE_UPCOMING_EXERCISE_LIST_FORMAT + LS + LS
                    + "Type " + QUOTATION + HelpCommand.COMMAND_WORD + QUOTATION
                    + " if you need more details on how to edit each list!";
    public static final String MESSAGE_EDIT_BANK_NEED_DETAILS =
            "Please specify what you wish to change about this item using the following prefixes:" + INDENTED_LS
                    + MESSAGE_NAME_FORMAT + INDENTED_LS
                    + MESSAGE_ITEM_CALORIES_FORMAT;
    public static final String MESSAGE_EDIT_UPCOMING_EXERCISE_LIST_NEED_DETAILS =
            "Please specify what you wish to change about this item using the following prefixes:" + INDENTED_LS
                    + MESSAGE_NAME_FORMAT + INDENTED_LS
                    + MESSAGE_ITEM_CALORIES_FORMAT + INDENTED_LS
                    + MESSAGE_ITEM_DATE_FORMAT;

    //profile command
    public static final String MESSAGE_PROFILE_COMMAND_INVALID_FORMAT =
            "Oops! Invalid format! Trying to update your profile?" + LS
                    + "Type " + QUOTATION + Command.COMMAND_WORD_PROFILE + QUOTATION
                    + " followed by any of the following prefixes to specify what you are changing!:" + LS + INDENTED_LS
                    + MESSAGE_NAME_FORMAT + INDENTED_LS
                    + MESSAGE_HEIGHT_FORMAT + INDENTED_LS
                    + MESSAGE_AGE_FORMAT + INDENTED_LS
                    + MESSAGE_GOAL_FORMAT + INDENTED_LS
                    + MESSAGE_GENDER_FORMAT + INDENTED_LS
                    + MESSAGE_ACTIVITY_FACTOR_FORMAT + LS + LS
                    + "You can use any number of prefixes depending on your needs but please include at least one!" + LS
                    + "E.G: profile n/John";
}


