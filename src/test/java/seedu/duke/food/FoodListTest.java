package seedu.duke.food;

import org.junit.jupiter.api.Test;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.item.food.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodListTest {
    @Test
    void addFoodUsingFoodClassParameter_foodClassParameter_expectExistsInList() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        assertEquals("chicken rice (607 cal) @ 10:20, 16 Oct 2021",
                foodList.getItem(foodList.getSize() - 1).toStringWithDate());
    }
    
    @Test
    void printNonEmptyFoodList_nonEmptyFoodList_expectCorrectOutputString() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-19 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1650", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1430", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.sortList();
        System.out.println(foodList.convertToString());
    }

    @Test
    void printEmptyFoodList_emptyFoodList_expectEmptyString() {
        FoodList foodList = new FoodList();
        assertEquals("", foodList.convertToString());
    }

    @Test
    void deleteExistingFoodItem_validIndexInput_expectDeleteSuccessful() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-19 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1650", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1430", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.sortList();
        foodList.deleteItem(1, LocalDateTime.parse("16-10-2021 1020",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).toLocalDate(),
                LocalDateTime.parse("16-10-2021 1020",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")).toLocalTime());
        System.out.println(foodList.convertToString());
        assertNotEquals(8, foodList.getSize());
    }

    @Test
    void deleteNonExistingFoodItem_invalidIndexInput_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteItem(3));
    }

    @Test
    void deleteNegativeIndexFoodItem_negativeIndexInput_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteItem(-1));
    }

    @Test
    void deleteNonDigitIndexFoodItem_nonDigitIndexInput_expectNumberFormatException() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        assertThrows(NumberFormatException.class, () -> foodList.deleteItem(Integer.parseInt("a")));
    }

    @Test
    void deleteFromEmptyFoodList_emptyFoodList_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteItem(1));
    }

    @Test
    void deleteAllFoodItems_callDeleteAllMethod_expectEmptyList() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607));
        foodList.addItem(new Food("yong tau foo", 536));
        foodList.addItem(new Food("mcspicy alacarte", 528));
        foodList.addItem(new Food("char kway teow", 744));
        foodList.clearList();
        assertEquals(0, foodList.getSize());
    }

    @Test
    void deleteAllFromEmptyFoodList_emptyFoodList_expectEmptyList() {
        FoodList foodList = new FoodList();
        foodList.clearList();
        assertEquals(0, foodList.getSize());
    }

    @Test
    void totalFoodCalories_callTotalCaloriesMethod_expectSumOfFoodCalories() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607));
        foodList.addItem(new Food("yong tau foo", 536));
        foodList.addItem(new Food("mcspicy alacarte", 528));
        foodList.addItem(new Food("char kway teow", 744));
        assertEquals(2415, foodList.getTotalCalories());
    }

    @Test
    void totalFoodCalories_emptyFoodList_expectZeroSum() {
        FoodList foodList = new FoodList();
        assertEquals(0, foodList.getTotalCalories());
    }

    @Test
    void totalFoodCaloriesForSingleDate_oneLocalDateInput_expectSumOfCalorieOnThatDay() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-19 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1650", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("McDonald’s Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1430", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck’s Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        assertEquals(1683, foodList.getTotalCaloriesWithDate(
                LocalDate.parse("16-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void printFoodListByOneGivenDate_inputLocalDate_expectFoodListOfThatDayOnly() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1020", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-19 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1650", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1430", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        System.out.println(foodList.convertToStringBySpecificDate(
                LocalDate.parse("16-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void printFoodListWithDifferentDateAndTimePeriod_inputDateAndTime_expectFoodListWithDateAndTimePeriodSeparated() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1320", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-17 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1850", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1030", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("17-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("fish soup", 300,
                LocalDateTime.parse("16-10-2021 1920", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("nasi lemak", 430,
                LocalDateTime.parse("16-10-2021 0120", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.sortList();
        System.out.println(foodList.convertToString());
    }

    @Test
    void countSupperWithNonEmptyFoodList_callCountSupperMethod_expectCorrectIntegerSupperCount() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1320", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-17 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1850", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1030", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("17-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("fish soup", 300,
                LocalDateTime.parse("16-10-2021 1920", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("nasi lemak", 430,
                LocalDateTime.parse("16-10-2021 0120", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("cheese chicken burger", 430,
                LocalDateTime.parse("18-10-2021 0420", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        assertEquals(2, foodList.getSupperCount());
    }

    @Test
    void printFoodListBySpecificDateAndTime_inputDateAndTimePeriod_expectFoodListForTheDateAndTimePeriodOnly() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("2021-10-16 1320", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-17 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("mcspicy alacarte", 528,
                LocalDateTime.parse("2021-10-18 1850", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("maggie mee", 276,
                LocalDateTime.parse("16-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("McDonald's Medium Fries", 380,
                LocalDateTime.parse("16-10-2021 1030", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("Starbuck's Old-fashioned Glazed Donut", 420,
                LocalDateTime.parse("17-10-2021 1020", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("fish soup", 300,
                LocalDateTime.parse("16-10-2021 1920", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("nasi lemak", 430,
                LocalDateTime.parse("16-10-2021 0120", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("cheese chicken burger", 430,
                LocalDateTime.parse("18-10-2021 0420", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.sortList();
        System.out.println(foodList.convertToStringBySpecificDateAndTime(
                LocalDate.parse("16-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                TimePeriod.Morning));
    }

    @Test
    void printFoodListBySpecificDateWithEmptyFoodList_emptyFoodList_expectErrorMessage() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("17-10-2021 2359", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        assertEquals("There is no food item found by the given date", foodList.convertToStringBySpecificDate(
                LocalDate.parse("16-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void printFoodListBySpecificDateAndTimePeriodWithEmptyFoodList_emptyFoodList_expectErrorMessage() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("17-10-2021 2359", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        assertEquals("There is no food item found by the given date and time period",
                foodList.convertToStringBySpecificDateAndTime(
                        LocalDate.parse("16-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), TimePeriod.Morning));
    }

    @Test
    void addItemFromOtherNonEmptyFoodList_callAddAllMethod_expectSizeIncrease() {
        FoodList foodList = new FoodList();
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("25-10-2021 2359", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-26 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("yong tau foo", 536,
                LocalDateTime.parse("2021-10-17 1450", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        foodList.addItem(new Food("chicken rice", 607,
                LocalDateTime.parse("23-10-2021 2359", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addItem(new Food("char kway teow", 744,
                LocalDateTime.parse("2021-10-18 1900", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
        FoodList subList = new FoodList();
        subList.addItem(new Food("nasi lemak", 430,
                LocalDateTime.parse("16-10-2021 0120", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        subList.addItem(new Food("cheese chicken burger", 430,
                LocalDateTime.parse("18-10-2021 0420", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        foodList.addAll(subList);
        System.out.println(foodList.convertToString());
        assertEquals(4, foodList.getSize());
    }
}