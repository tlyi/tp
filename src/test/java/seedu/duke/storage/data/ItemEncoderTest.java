package seedu.duke.storage.data;

import org.junit.jupiter.api.Test;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemEncoderTest {

    @Test
    void encodeItems_validInputsInUnsortedOrder_expectSortedOrderWhenEncoded() {
        FoodList foodList = new FoodList();

        //The food items are not added in order
        foodList.addItem(new Food("peanut butter waffle", 403,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("peanut butter waffle1", 403,
                LocalDateTime.parse("2021-11-16 1030", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("peanut butter waffle2", 403,
                LocalDateTime.parse("2021-12-16 1040", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("peanut butter waffle3", 403,
                LocalDateTime.parse("2021-09-16 1050", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        ArrayList<String> testingList = ItemEncoder.encode(foodList);

        //But the expected list should be in order when encoded
        ArrayList<String> expectedList = new ArrayList<>() {
            {
                add("F|peanut butter waffle3|403|16-09-2021 1050");
                add("F|peanut butter waffle|403|16-10-2021 1020");
                add("F|peanut butter waffle1|403|16-11-2021 1030");
                add("F|peanut butter waffle2|403|16-12-2021 1040");
            }
        };
        assertEquals(expectedList, testingList);
    }

    @Test
    void encodeExerciseItems_validInputsInUnsortedOrder_expectSortedOrderWhenEncoded() {
        ExerciseList exerciseList = new ExerciseList();

        //The exercise items are not added in order
        exerciseList.addItem(new Exercise("Running 21k", 1200,
                LocalDate.parse("19-12-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        exerciseList.addItem(new Exercise("Running 10k", 780,
                LocalDate.parse("19-11-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        exerciseList.addItem(new Exercise("Running 2.4k", 250,
                LocalDate.parse("19-09-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        exerciseList.addItem(new Exercise("Running 5k", 530,
                LocalDate.parse("19-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        ArrayList<String> testingList = ItemEncoder.encode(exerciseList);

        //But the expected list should be in order when encoded
        ArrayList<String> expectedList = new ArrayList<>() {
            {
                add("E|Running 2.4k|250|19-09-2021");
                add("E|Running 5k|530|19-10-2021");
                add("E|Running 10k|780|19-11-2021");
                add("E|Running 21k|1200|19-12-2021");
            }
        };
        assertEquals(expectedList, testingList);
    }

}