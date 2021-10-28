package seedu.duke.data.item.food;

import seedu.duke.data.item.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Food extends Item {
    public static final String FOOD_TYPE = "F";
    private static final int EARLIEST_NIGHT_HOUR = 21;
    private static final int LATEST_NIGHT_HOUR = 4;
    private static final int EARLIEST_EVENING_HOUR = 17;
    private static final int LATEST_EVENING_HOUR = 20;
    private static final int EARLIEST_AFTERNOON_HOUR = 12;
    private static final int LATEST_AFTERNOON_HOUR = 16;
    private static final int EARLIEST_MORNING_HOUR = 5;
    private static final int LATEST_MORNING_HOUR = 11;
    public static final String MESSAGE_FOOD = "%s @ %s";
    protected LocalDateTime dateTime;
    protected TimePeriod timePeriod;

    /**
     * Constructor for the food object when the date and time are not provided.
     *
     * @param name     The name or description of the food
     * @param calories The calorie of the food consumed
     */
    public Food(String name, int calories) {
        super(name, calories);
        this.dateTime = LocalDateTime.now(); //sets to current date and time
        setTimePeriod(this.dateTime);
    }

    /**
     * Constructor for the food object when all attributes are provided.
     *
     * @param name     The name or description of the food
     * @param calories The calorie intake from the food
     * @param dateTime The date and time when the food is consumed
     */
    public Food(String name, int calories, LocalDateTime dateTime) {
        super(name, calories);
        this.dateTime = dateTime;
        setTimePeriod(this.dateTime);
    }

    //====================Getter and Setter methods=========================
    /**
     * Gets the date and time of the food consumed.
     *
     * @return The date and time of the food consumed in LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Updates the date and time of the food consumed.
     *
     * @param dateTime The new date and time of the food consumed in LocalDateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        setTimePeriod(this.dateTime);
    }

    /**
     * Gets the date of the food consumed.
     *
     * @return The date of the food consumed in LocalDate
     */
    @Override
    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    /**
     * Updates the date of the food consumed.
     *
     * @param date The new date of the food consumed in LocalDate
     */
    public void setDate(LocalDate date) {
        LocalTime time = this.dateTime.toLocalTime();
        setDateTime(date.atTime(time));
    }

    /**
     * Gets the time of the food consumed.
     *
     * @return The time of the food consumed in LocalTime
     */
    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }

    /**
     * Updates the time of the food consumed.
     *
     * @param time The new time of the food consumed
     */
    public void setTime(LocalTime time) {
        LocalDate date = this.dateTime.toLocalDate();
        setDateTime(date.atTime(time));
    }

    /**
     * Gets the time period of the food consumed.
     *
     * @return TimePeriod enum which represents the time period
     */
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    //====================To String methods=========================

    /**
     * Converts the Food item to string for printing in the Food List.
     *
     * @return String pattern of Food item with time but no date
     */
    @Override
    public String toString() {
        return String.format(MESSAGE_FOOD, super.toString(), this.getTime().format(TIME_FORMATTER));
    }

    /**
     * Converts the food item in string format which only shows the food item name description and calorie.
     *
     * @return The food item name and calorie display
     */
    @Override
    public String toStringWithoutDateAndTime() {
        return super.toString();
    }

    /**
     * Converts the Food item to string for printing after adding or deleting food.
     *
     * @return String pattern of Food item with time and date
     */
    public String toStringWithDate() {
        return this + ", " + this.getDate().format(DATE_FORMAT_FOR_PRINTING);
    }

    /**
     * Converts the food to external file string format.
     *
     * @return Name, calorie, date and time of the food in string
     */
    @Override
    public String toFileTextString() {
        return FOOD_TYPE
                + super.toFileTextString()
                + FILE_TEXT_DELIMITER
                + this.getDateTime().format(DATE_TIME_FORMATTER);
    }

    //====================Private methods=========================

    /**
     * Sets or updates time period according to the time provided in dateTime.
     *
     * @param dateTime Date and time provided
     */
    private void setTimePeriod(LocalDateTime dateTime) {
        if (isMorning(dateTime)) {
            this.timePeriod = TimePeriod.Morning;
        } else if (isAfternoon(dateTime)) {
            this.timePeriod = TimePeriod.Afternoon;
        } else if (isEvening(dateTime)) {
            this.timePeriod = TimePeriod.Evening;
        } else if (isNight(dateTime)) {
            this.timePeriod = TimePeriod.Night;
        } else {
            this.timePeriod = null;
        }
    }

    private boolean isNight(LocalDateTime dateTime) {
        return dateTime.getHour() >= EARLIEST_NIGHT_HOUR || dateTime.getHour() <= LATEST_NIGHT_HOUR;
    }

    private boolean isEvening(LocalDateTime dateTime) {
        return dateTime.getHour() >= EARLIEST_EVENING_HOUR && dateTime.getHour() <= LATEST_EVENING_HOUR;
    }

    private boolean isAfternoon(LocalDateTime dateTime) {
        return dateTime.getHour() >= EARLIEST_AFTERNOON_HOUR && dateTime.getHour() <= LATEST_AFTERNOON_HOUR;
    }

    private boolean isMorning(LocalDateTime dateTime) {
        return dateTime.getHour() >= EARLIEST_MORNING_HOUR && dateTime.getHour() <= LATEST_MORNING_HOUR;
    }

}
