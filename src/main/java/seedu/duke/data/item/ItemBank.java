package seedu.duke.data.item;

import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;

import java.util.ArrayList;

/**
 * Represents a list of Items, meant to store a repository of user-defined Exercise items or Food items.
 * Contains all the methods for actions that can be done on the list.
 */
public class ItemBank {
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";
    protected ArrayList<Item> internalItems = new ArrayList<>();

    /**
     * Adds new item to the item bank.
     *
     * @param item The new item to add
     * @throws DuplicateItemInBankException Throws this error when duplicate items are found
     */
    public void addItem(Item item) throws DuplicateItemInBankException {
        checkNoDuplicateItemName(item.getName());
        internalItems.add(item);
    }

    /**
     * Returns item with the given index in the item bank.
     *
     * @param index The index of the item
     * @return The item with the given index
     */
    public Item getItem(int index) {
        return internalItems.get(index);
    }

    /**
     * Deletes the item in the bank.
     *
     * @param index The index of the item to delete
     * @return The deleted item
     */
    public Item deleteItem(int index) {
        return internalItems.remove(index);
    }

    /**
     * Deletes all the item inside the item bank.
     */
    public void clearList() {
        internalItems.clear();
    }

    /**
     * Returns the size of the item bank.
     *
     * @return The size of the array list attribute
     */
    public int getSize() {
        return internalItems.size();
    }

    /**
     * Converts the array list to a string for printing purpose.
     *
     * @return The array list in string
     */
    public String convertToString() {
        StringBuilder listToString = new StringBuilder();
        for (int i = 0; i < internalItems.size(); i++) {
            listToString.append(TAB).append(i + 1).append(". ")
                    .append(internalItems.get(i).toStringWithoutDateAndTime()).append(LS);
        }
        return listToString.toString().stripTrailing();
    }

    /**
     * Gets the calories of the item which has the same name as the input item.
     *
     * @param inputName The input item name in string
     * @return The calories value of the item which has the same name as the input item.
     * @throws ItemNotFoundInBankException Throws this exception when there is no item which has the same name
     *                                     as the input item
     */
    public int findCalorie(String inputName) throws ItemNotFoundInBankException {
        Item matchingItem = internalItems
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(inputName))
                .findAny()
                .orElse(null);
        if (matchingItem == null) {
            throw new ItemNotFoundInBankException();
        }
        return matchingItem.getCalories();
    }

    /**
     * Checks if there already exists an item in the bank with the same name as the input.
     * Used before adding/editing items to/from the bank.
     *
     * @param inputName String to check against
     * @throws DuplicateItemInBankException Throws this exception if there already exists an item in the bank with
     *                                      the same name (case-insensitive).
     */
    public void checkNoDuplicateItemName(String inputName) throws DuplicateItemInBankException {
        long numOfMatchingItems = internalItems
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(inputName))
                .count();
        if (numOfMatchingItems > 0) {
            throw new DuplicateItemInBankException();
        }
    }
}
