package seedu.duke.data.item.exercise;

import seedu.duke.data.item.Item;

import java.time.LocalDate;

public class Exercise extends Item {
    public static final String EXERCISE_TYPE = "E";
    public static final String AT = " @ ";
    protected LocalDate date;

    /**
     * Constructor for exercise object when the date is not provided.
     *
     * @param name     The name or description of the exercise.
     * @param calories The calorie burnt from the exercise.
     */
    public Exercise(String name, int calories) {
        super(name, calories);
        this.date = LocalDate.now();
    }

    /**
     * Constructor for the exercise object when all attributes are provided.
     *
     * @param name     The name or description of the exercise
     * @param calories The calorie burnt from the exercise
     * @param date     The date when the exercise is taken
     */
    public Exercise(String name, int calories, LocalDate date) {
        super(name, calories);
        this.date = date;
    }


    //====================Getter and Setter methods=========================

    /**
     * Gets the date of the exercise taken.
     *
     * @return The date of the exercise done in LocalDate
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Updates the date of the exercise taken.
     *
     * @param date The new date of the exercise done in LocalDate
     */
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }


    //====================To String methods=========================

    /**
     * Converts the exercise item in string format which only shows the exercise item name description and calorie.
     *
     * @return The exercise item name and calorie display
     */
    @Override
    public String toStringWithoutDateAndTime() {
        return super.toString();
    }

    /**
     * Converts the Exercise item to string for printing after adding or deleting Exercise.
     *
     * @return String pattern of Food item with time and date
     */
    public String toStringWithDate() {
        return super.toString() + AT + this.getDate().format(DATE_FORMAT_FOR_PRINTING);
    }

    /**
     * Converts the exercise to external file string format.
     *
     * @return Name, calorie and date of the exercise in string
     */
    @Override
    public String toFileTextString() {
        return EXERCISE_TYPE
                + super.toFileTextString()
                + FILE_TEXT_DELIMITER
                + this.getDate().format(DATE_FORMATTER_FOR_STORAGE);
    }
}
