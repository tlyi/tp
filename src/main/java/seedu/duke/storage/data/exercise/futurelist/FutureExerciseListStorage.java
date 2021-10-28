package seedu.duke.storage.data.exercise.futurelist;

import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;

/**
 * Storage that handles the saving and loading of data files of upcoming exercises in future exercise storage.
 */
public class FutureExerciseListStorage extends Storage implements UpcomingStorageInterface {

    /**
     * Constructor for future exercise list storage.
     *
     * @param filePath of where the future exercise list should be stored
     */
    public FutureExerciseListStorage(String filePath) {
        this.filePath = filePath;
        this.fileName = getFileName(filePath);
    }

    @Override
    public FutureExerciseList loadFutureExerciseList() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        return readFromFutureListFile();
    }

    private FutureExerciseList readFromFutureListFile() throws UnableToReadFileException {
        try {
            return FutureExerciseListDecoder.retrieveUpcomingListFromData(filePath);
        } catch (FileNotFoundException e) {
            logger.log(Level.FINE, "The path is missing ", filePath);
            throw new UnableToReadFileException(fileName);
        }
    }

    @Override
    public void saveFutureExerciseList(FutureExerciseList futureExercises) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ItemEncoder.encode(futureExercises));
    }

}
