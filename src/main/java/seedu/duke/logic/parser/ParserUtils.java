package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.ParamInvalidException;
import seedu.duke.logic.parser.exceptions.ParamMissingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains utility methods used by Parser classes to extract relevant parameters.
 */
public class ParserUtils {
    protected static final Logger logger = Logger.getLogger(ParserUtils.class.getName());

    protected static boolean isSevenDaysBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now().minusDays(7));
    }

    protected static boolean hasRequiredParams(String params, String... prefixes) {
        for (String prefix : prefixes) {
            if (!params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Extracts the item type prefix for command words that are common across item types (add, delete, view).
     *
     * @param params String containing all parameters
     * @return String that is one of the item type prefixes
     * @throws ItemNotSpecifiedException If parameter does not contain any of the expected prefixes,
     *                                   or contains more than one.
     */
    protected static String extractItemTypePrefix(String params) throws ItemNotSpecifiedException {
        boolean isExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_EXERCISE
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isFood =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_FOOD
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isUpcomingExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_UPCOMING_EXERCISE
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isExerciseBank =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_EXERCISE_BANK
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isFoodBank =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_FOOD_BANK
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isRecurringExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_RECURRING
                        + Command.COMMAND_PREFIX_DELIMITER);

        boolean isItemSpecified = ParserUtils.checkIsItemSpecified(isExercise, isFood, isUpcomingExercise,
                isExerciseBank, isFoodBank, isRecurringExercise);

        if (!isItemSpecified) {
            logger.log(Level.FINE, "Detected none or more than one item");
            throw new ItemNotSpecifiedException();
        } else if (isExercise) {
            return Command.COMMAND_PREFIX_EXERCISE;
        } else if (isFood) {
            return Command.COMMAND_PREFIX_FOOD;
        } else if (isUpcomingExercise) {
            return Command.COMMAND_PREFIX_UPCOMING_EXERCISE;
        } else if (isExerciseBank) {
            return Command.COMMAND_PREFIX_EXERCISE_BANK;
        } else if (isRecurringExercise) {
            return Command.COMMAND_PREFIX_RECURRING;
        } else {
            assert isFoodBank : "Must be food bank if all above conditions are not satisfied";
            return Command.COMMAND_PREFIX_FOOD_BANK;
        }
    }


    protected static boolean checkIsItemSpecified(boolean... paramBool) {
        int numberOfParams = 0;
        for (boolean isParam : paramBool) {
            if (isParam) {
                numberOfParams += 1;
            }
        }
        return numberOfParams == 1 ? true : false; // item must be exactly 1
    }

    /**
     * Extracts only the parameter required so that any additional parameter
     * specified behind this string (if any) is removed.
     * E.g. "John Doe w/20" is returned as "John Doe".
     * NOTE: This is why users are not allowed to include the character "/" in their inputs
     * other than to specify a parameter.
     */
    protected static String extractRelevantParameter(String params) {
        if (params.contains(Command.COMMAND_PREFIX_DELIMITER)) {
            return params.substring(0, params.indexOf(Command.COMMAND_PREFIX_DELIMITER) - 1).trim();
        } else {
            return params;
        }
    }

    protected static String extractItemDescription(String params, String prefix)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix = params.split(prefix + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String description = extractRelevantParameter(stringAfterPrefix);
            if (description.equals(ParserMessages.EMPTY)) {
                logger.log(Level.FINE, "Detected empty description");
                throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
            }
            return description;
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, String.format("Detected missing command prefix (%s)", prefix));
            throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
        }
    }


    protected static int extractItemCalories(String params, boolean isRequired)
            throws ParamInvalidException, ParamMissingException {
        try {
            if (params.contains(Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER)) {
                String stringAfterPrefix =
                        params.split(Command.COMMAND_PREFIX_CALORIES
                                + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
                String caloriesString = stringAfterPrefix.split(" ", 2)[0];
                return Integer.parseInt(caloriesString);
            } else {
                if (isRequired) {
                    logger.log(Level.FINE, "Detected missing calories prefix");
                    throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_CALORIES_INFO);
                } else {
                    logger.log(Level.FINE, "Detected missing calories prefix but calories not required, "
                            + "returning null calorie value");
                    return Command.NULL_CALORIES;
                }
            }
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit calories input");
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_CALORIES_INFO);
        }
    }

    protected static String extractName(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_NAME
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String name = extractRelevantParameter(stringAfterPrefix).trim();
            if (name.equals(ParserMessages.EMPTY)) {
                logger.log(Level.FINE, "Detected empty name input.");
                throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_NAME_EMPTY_STRING);
            }
            return name;
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing name prefix, returning empty string.");
            return Command.NULL_STRING;
        }
    }

    protected static double extractHeight(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_HEIGHT
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String doubleString = stringAfterPrefix.split(" ", 2)[0];
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit height input.");
            throw new ParamInvalidException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "height"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing height prefix, return 0 height");
            return Command.NULL_DOUBLE;
        }
    }

    protected static double extractWeight(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_WEIGHT
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String doubleString = stringAfterPrefix.split(" ", 2)[0];
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit weight input.");
            throw new ParamInvalidException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "weight"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing weight prefix, return 0 weight");
            return Command.NULL_DOUBLE;
        }
    }

    protected static int extractCalorieGoal(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_GOAL
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit goal input.");
            throw new ParamInvalidException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "goal"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing goal prefix, return 0 goal");
            return Command.NULL_INT;
        }
    }

    protected static int extractAge(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_AGE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit age input.");
            throw new ParamInvalidException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "age"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing age prefix, return 0 age");
            return Command.NULL_INT;
        }
    }

    protected static int extractActivityFactor(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_ACTIVITY_FACTOR
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit activity factor input.");
            throw new ParamInvalidException(String.format(
                    ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "activity factor"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing activity factor prefix, return 0 activity factor");
            return Command.NULL_INT;
        }
    }

    protected static char extractGender(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_GENDER
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            return stringAfterPrefix.charAt(0);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected missing gender prefix, return null gender");
            return Command.NULL_CHAR;
        }
    }

    //@@author xingjie99
    protected static LocalDate extractStartDate(String params)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_START_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.FINE, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected empty start date input after prefix but date is required!");
            throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_START_DATE);
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }

    //@@author xingjie99
    protected static LocalDate extractEndDate(String params)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_END_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.FINE, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected empty end date input after prefix but date is required!");
            throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_END_DATE);
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }
    //@@author

    protected static LocalDate extractDate(String params, boolean isRequired)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.FINE, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            if (isRequired) {
                logger.log(Level.FINE, "Detected empty date input after prefix but date is required!");
                throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_DATE);
            } else {
                logger.log(Level.FINE, "Detected empty date input after prefix, assuming date to be now");
                return LocalDate.now();
            }
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }

    protected static LocalTime extractTime(String params, boolean isRequired)
            throws ParamMissingException, ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_TIME
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String timeString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.FINE, String.format("time string detected is: %s", timeString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.TIME_FORMAT);
            return LocalTime.parse(timeString, formatter);
        } catch (IndexOutOfBoundsException e) {
            if (isRequired) {
                logger.log(Level.FINE, "Detected empty time input after prefix but time is required!");
                throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_TIME);
            } else {
                logger.log(Level.FINE, "Detected empty time input after prefix, assuming time to be now");
                return LocalTime.now();
            }
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_TIME_FORMAT);
        }
    }

    protected static LocalDateTime extractDateTime(String params) throws ParamInvalidException, ParamMissingException {
        final LocalTime time = extractTime(params, false);
        final LocalDate date = extractDate(params, false);
        return date.atTime(time);
    }

    //@@author xingjie99
    protected static ArrayList<Integer> extractDayOfTheWeek(String params)
            throws ParamMissingException, ParamInvalidException {
        ArrayList<Integer> dayOfTheWeek = new ArrayList<>();
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_DAY_OF_THE_WEEK
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            for (int i = 0; i < dateString.length(); i++) {
                int day = Integer.parseInt(String.valueOf(dateString.charAt(i)));
                logger.log(Level.FINE, String.format("day detected: %s", day));
                if (day >= ParserMessages.MONDAY && day <= ParserMessages.SUNDAY) { //between monday and sunday
                    if (dayOfTheWeek.contains(day)) {
                        throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK);
                    }
                    dayOfTheWeek.add(day);
                } else {
                    throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected empty day input after prefix but day is required!");
            throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_DAY_OF_THE_WEEK);
        } catch (NumberFormatException e) {
            logger.log(Level.FINE, "Detected non-digit calories input");
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
        }
        return dayOfTheWeek;
    }
    //@author

    protected static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    protected static int extractItemIndex(String params, String prefix)
            throws ParamInvalidException, ParamMissingException {
        try {
            final String itemNumString = ParserUtils.extractItemDescription(params, prefix).split(" ")[0].trim();
            final int itemIndex = convertItemNumToItemIndex(Integer.parseInt(itemNumString));
            if (itemIndex < 0) {
                throw new NumberFormatException();
            }
            return itemIndex;
        } catch (ParamInvalidException | ParamMissingException e) {
            throw new ParamMissingException(ParserMessages.MESSAGE_ERROR_NO_ITEM_NUM);
        } catch (NumberFormatException e) {
            throw new ParamInvalidException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
        }
    }

    protected static int convertItemNumToItemIndex(int itemNum) {
        return itemNum - 1;
    }


    /**
     * Returns the number of parameters detected that is valid for the specific command.
     * This is required as some parameters are optional, therefore an absolute number cannot be expected.
     *
     * @param params   User input string containing all parameters
     * @param prefixes Variable number of prefixes that is valid for the specific command
     * @return Number of parameters detected that is valid for the specific command
     */
    protected static int getNumberOfCorrectParamsDetected(String params, String... prefixes) {
        int count = 0;
        for (String prefix : prefixes) {
            if (params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                count++;
            }
        }
        logger.log(Level.FINE, String.format("no. of corrected params detected: %s", count));
        return count;
    }

    /**
     * Returns true if there are too many '/' characters in the parameter string.
     *
     * @param params   User input string containing all parameters
     * @param prefixes Variable number of prefixes that is valid for the specific command
     */
    protected static boolean hasExtraDelimiters(String params, String... prefixes) {
        final int expectedNum = getNumberOfCorrectParamsDetected(params, prefixes);
        int numOfDelimiters = 0;
        for (int i = 0; i < params.length(); i++) {
            if (params.charAt(i) == Command.COMMAND_PREFIX_DELIMITER.charAt(0)) {
                numOfDelimiters++;
            }
        }
        logger.log(Level.FINE, String.format("no. of delimiters detected: %s", numOfDelimiters));
        return numOfDelimiters > expectedNum;
    }
}
