package seedu.duke;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.logic.LogicManager;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;

import java.time.LocalDate;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {
    private DataManager dataManager;
    private Ui ui;
    private StorageManager storageManager;
    private LogicManager logicManager;


    /**
     * Entry point of the application.
     */
    public static void main(String[] args) throws UnableToWriteFileException {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) throws UnableToWriteFileException {
        start();
        checkAndCreateProfile();
        loadsFutureExercisesToList();
        enterTaskModeUntilByeCommand();
        exit();
    }

    //@@author tttyyzzz
    private void checkAndCreateProfile() {
        dataManager.setProfile(new StartState(dataManager.getProfile(), storageManager, ui).checkAndCreateProfile());
    }
    //@@author

    /**
     * Initialises the application by creating the required objects and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storageManager = new StorageManager();
        this.ui = new Ui();
        try {
            dataManager = storageManager.loadAll();
        } catch (UnableToReadFileException e) {
            dataManager = new DataManager();
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        this.logicManager = new LogicManager(storageManager, dataManager);
        ui.printStartMessage(
                dataManager.getProfile().checkProfileComplete(),
                dataManager.getProfile().checkProfilePresent());
    }


    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        CommandResult result;
        do {
            String userInput = ui.getUserInput();
            result = logicManager.execute(userInput);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!result.isBye());
    }

    //@@author xingjie99

    /**
     * Check whether the dates of the exercises in the future exercise list have passed.
     * If the dates have passed, move the exercises in the exercise list.
     */
    private void loadsFutureExercisesToList() throws UnableToWriteFileException {
        int index = 0;
        LocalDate today = LocalDate.now();
        while (dataManager.getFutureExerciseItems().getSize() != 0
                && (dataManager.getFutureExerciseItems().getItem(index).getDate().isBefore(today)
                || dataManager.getFutureExerciseItems().getItem(index).getDate().isEqual(today))) {
            String name = dataManager.getFutureExerciseItems().getItem(index).getName();
            int calories = dataManager.getFutureExerciseItems().getItem(index).getCalories();
            LocalDate date = dataManager.getFutureExerciseItems().getItem(index).getDate();
            dataManager.getExerciseItems().addItem(new Exercise(name, calories, date));
            dataManager.getFutureExerciseItems().deleteItem(index);
            storageManager.saveExerciseList(dataManager.getExerciseItems());
            storageManager.saveFutureExerciseList(dataManager.getFutureExerciseItems());
        }
    }
    //@@author


    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
