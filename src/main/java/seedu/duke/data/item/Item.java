package seedu.duke.data.item;

import seedu.duke.data.item.food.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Item is an abstract class that contains methods used in common in Food and Exercise classes.
 */
public abstract class Item {
    public static final String FILE_TEXT_DELIMITER = "|";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER_FOR_STORAGE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public static final DateTimeFormatter DATE_FORMAT_FOR_PRINTING = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected String name;
    protected int calories;

    /**
     * Constructor for item object.
     *
     * @param name     The name or description of the item
     * @param calories The calorie intake/burnt from the item
     */
    public Item(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    //====================Getter and Setter Methods=========================

    /**
     * Gets the name or description of the item.
     *
     * @return The name or description of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the calorie intake/burnt for the item.
     *
     * @return The calorie intake/burnt from the item
     */
    public int getCalories() {
        return this.calories;
    }

    /**
     * Updates the name or description of the item.
     *
     * @param name The new name or description of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the calorie intake/burnt from the item.
     *
     * @param calories The new calorie intake/burnt from the item
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    //====================Non-abstract methods=========================

    /**
     * Converts the item in correct string format.
     *
     * @return The item name and calorie displayed in string
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalories() + " cal)";
    }

    /**
     * Converts the item to external file string format.
     *
     * @return Name and calorie of the item in string
     */
    public String toFileTextString() {
        return FILE_TEXT_DELIMITER + this.getName() + FILE_TEXT_DELIMITER + this.getCalories();
    }

    //====================Abstract methods=========================

    /**
     * Converts the item in string format which only shows the item name description and calorie,
     * will be implemented in Food and Exercise classes.
     *
     * @return The item name and calorie display
     */
    public abstract String toStringWithoutDateAndTime();

    /**
     * Converts the item in the string format same as toString method but with date.
     * @return The item name, calorie and date in string
     */
    public abstract String toStringWithDate();

    /**
     * Gets the date of the item, will be implemented in Food and Exercise classes.
     *
     * @return The date of the item
     */
    public abstract LocalDate getDate();

    /**
     * Updates the date. To be implemented in Exercise class.
     *
     * @param date The new date of the exercise done in LocalDate
     */
    public abstract void setDate(LocalDate date);


    //====================Food Item methods=========================

    /**
     * Gets the time period of the item, will be implemented in Food class.
     *
     * @return The time period of the food item
     */
    public TimePeriod getTimePeriod() {
        return null;
    }

    /**
     * Gets the date and time of the item, will be implemented in Food class.
     *
     * @return The date and time of the food item in LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return null;
    }
}
