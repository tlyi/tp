package seedu.duke.storage.data.food.foodbank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ItemBankDecoder;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;

/**
 * A Storage class that handles the saving and loading of the FoodBank.
 */
public class FoodBankStorage extends Storage implements FoodBankStorageInterface {

    public static final String TYPE = "Food";

    /**
     * Constructs the food bank storage handler with its respective path.
     *
     * @param path the directory to save the food bank file
     */
    public FoodBankStorage(String path) {
        this.filePath = path;
        this.fileName = getFileName(path);
    }

    @Override
    public ItemBank loadFoodBank() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        return readFromFoodBankFile();
    }

    private ItemBank readFromFoodBankFile() throws UnableToReadFileException {
        try {
            return ItemBankDecoder.retrieveDataFromItemBank(filePath, TYPE);
        } catch (FileNotFoundException e) {
            logger.log(Level.FINE, "The path is missing ", filePath);
            throw new UnableToReadFileException(filePath);
        }
    }

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(foodBank));
    }
}
