package seedu.duke;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.logic.parser.exceptions.ParamMissingException;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;

public class StartState {

    public static final char MALE_CHAR = 'M';
    public static final String MESSAGE_MALE = "You are a male.";
    public static final String MESSAGE_FEMALE = "You are a female.";
    public static final String MESSAGE_NAME = "Nice name you have there! Hello %s";
    public static final String MESSAGE_HEIGHT = "Your height is %scm.";
    public static final String MESSAGE_WEIGHT = "Your weight is %skg.";
    public static final String MESSAGE_AGE = "You are %s years old.";
    public static final String MESSAGE_CALORIE_GOAL = "You calorie goal is %s cal.";
    public static final String MESSAGE_ACTIVITY_FACTOR = "Your activity factor is %s.";
    private Profile profile;
    private StorageManager storageManager;
    private Ui ui;

    public StartState(Profile profile, StorageManager storageManager, Ui ui) {
        this.profile = profile;
        this.storageManager = storageManager;
        this.ui = ui;
    }

    public static final String MESSAGE_INVALID_POSITIVE_INT_INPUT = "Invalid input, "
            + "please input a valid positive whole number";
    public static final String MESSAGE_INVALID_POSITIVE_DOUBLE_INPUT = "Invalid input,"
            + " please input a valid positive number";
    public static final String MESSAGE_EMPTY_INPUT = "Input cannot be empty";
    public static final String MESSAGE_INTRO_CALORIE_GOAL = "Please input your net calorie goal.";
    public static final String MESSAGE_INTRO_AGE = "How old are you?";
    public static final String MESSAGE_INTRO_GENDER = "What is your gender?(If you are a male, type 'm'"
            + ", if you are a female, type 'f')";
    public static final String MESSAGE_INTRO_WEIGHT = "What's your weight? (in kg)";
    public static final String MESSAGE_INTRO_NAME = "What's your name?";
    public static final String MESSAGE_INTRO_HEIGHT = "What's your height? (in cm)";
    public static final String MESSAGE_CREATE_PROFILE_SUCCESSFUL = "Profile created successfully";
    public static final String MESSAGE_INTRO_ACTIVITY_FACTOR = "In terms of activity level, how active are you?\n"
            + "Based on the rubics below, please key in 1 to 5 based on how active you are.\n"
            + "1 -> Sedentary - Little or no exercise\n"
            + "2 -> Lightly Active - Light exercise or sports, around 1-3 days a week\n"
            + "3 -> Moderately Active - Regular exercise or sports, around 3-5 days a week\n"
            + "4 -> Very Active - Frequent exercise or sports, around 6-7 days a week\n"
            + "5 -> If you are extra active - Sports or exercising is your passion and a physical jobscope.";

    /**
     * Check whether user's profile is complete.
     * If profile is complete, the program will exit this method.
     * If the profile is partially complete, it will assist user in completing the profile.
     * If all parameters of profile is incorrect or a new user, user is required to complete
     * all the particulars before saving their profile data.
     */
    public Profile checkAndCreateProfile() {
        if (profile.checkProfileComplete()) {
            return profile;
        }
        if (profile.checkProfilePresent()) {
            assert !profile.checkProfileComplete() : "profile is incomplete";
            repairProfile();
        } else {
            createNewProfile();
        }
        ui.formatMessageWithBottomDivider(MESSAGE_CREATE_PROFILE_SUCCESSFUL,
                ui.MESSAGE_DIRECT_HELP);
        return profile;
    }

    /**
     * Assists user in fixing remaining profile particulars.
     * The profile changes will be saved on every update.
     */
    private void repairProfile() {
        ui.formatMessageWithTopDivider();
        while (!profile.checkProfileComplete()) {
            try {
                if (!profile.getProfileName().isValid()) {
                    createNewProfileName(profile); // if user just enter and exit, it will cause his name to be null
                } else if (!profile.getProfileHeight().isValid()) {
                    createNewProfileHeight(profile);
                } else if (!profile.getProfileWeight().isValid()) {
                    createNewProfileWeight(profile);
                } else if (!profile.getProfileGender().isValid()) {
                    createNewProfileGender(profile);
                } else if (!profile.getProfileAge().isValid()) {
                    createNewProfileAge(profile);

                } else if (!profile.getProfileActivityFactor().isValid()) {
                    createNewProfileActivityFactor(profile);
                } else if (profile.getProfileCalorieGoal().isValid()) {
                    createNewProfileCalorieGoal(profile);
                }
                storageManager.saveProfile(this.profile);
            } catch (ParamMissingException e) {
                System.out.println(e.getMessage());
            } catch (UnableToWriteFileException e) {
                ui.formatMessageFramedWithDivider(e.getMessage());
            }
        }
        ui.formatMessageWithBottomDivider();
    }

    /**
     * Creates a new profile instance for new user.
     * Profile will be lost if user exits the program without setting up the profile.
     * Upon completing profile, the profile instance in Main will be replaced and stored in storage.
     */
    private void createNewProfile() {
        Profile newProfile = new Profile();
        ui.formatMessageWithTopDivider();
        while (!newProfile.checkProfileComplete()) {
            try {
                createNewProfileName(newProfile);
                createNewProfileHeight(newProfile);
                createNewProfileWeight(newProfile);
                createNewProfileGender(newProfile);
                createNewProfileAge(newProfile);
                createNewProfileCalorieGoal(newProfile);
                createNewProfileActivityFactor(newProfile);
            } catch (ParamMissingException e) {
                System.out.println(e.getMessage());
            }
        }
        this.profile = newProfile;
        try {
            storageManager.saveProfile(this.profile);
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        ui.formatMessageWithBottomDivider();
    }

    /**
     * Creates a valid profile activity factor for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileActivityFactor(Profile newProfile) throws ParamMissingException {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_ACTIVITY_FACTOR);
        while (!newProfile.getProfileActivityFactor().isValid()) {
            try {
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                int activityFactorInput = Integer.parseInt(userInput);
                newProfile.setProfileActivityFactor(new ActivityFactor(activityFactorInput));
                if (newProfile.getProfileActivityFactor().isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_ACTIVITY_FACTOR,
                                    newProfile.getProfileActivityFactor().getActivityFactor()));
                } else {
                    ui.formatMessageFramedWithDivider(profile.ERROR_ACTIVITY_FACTOR);
                }
                ;
            } catch (NumberFormatException e) {
                ui.formatMessageFramedWithDivider(MESSAGE_INVALID_POSITIVE_DOUBLE_INPUT);
            }
        }
    }

    /**
     * Creates a valid profile calorie goal for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileCalorieGoal(Profile newProfile) throws ParamMissingException {
        boolean checkInput = false;// check whether calorie goal has the correct input
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_CALORIE_GOAL);
        do {
            try {
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                int calorieGoalInput = Integer.parseInt(userInput);
                newProfile.setProfileCalorieGoal(new CalorieGoal(calorieGoalInput));
                //calorie input is responding
                //TODO: add a print statement to tell user input is incorrect
                if (newProfile.getProfileCalorieGoal().isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_CALORIE_GOAL,
                                    newProfile.getProfileCalorieGoal().getCalorieGoal()));
                } else {
                    ui.formatMessageFramedWithDivider(String.format(profile.ERROR_CALORIE_GOAL, calorieGoalInput));
                }
                checkInput = true;
            } catch (NumberFormatException e) {
                ui.formatMessageFramedWithDivider(MESSAGE_INVALID_POSITIVE_INT_INPUT);
            }
        } while (!checkInput || !newProfile.getProfileCalorieGoal().isValid());
    }

    /**
     * Creates a valid profile age for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileAge(Profile newProfile) throws ParamMissingException {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_AGE);
        while (!newProfile.getProfileAge().isValid()) {
            try {
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                int ageInput = Integer.parseInt(userInput);
                newProfile.setProfileAge(new Age(ageInput));
                if (newProfile.getProfileAge().isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_AGE,
                                    newProfile.getProfileAge().getAge()));
                } else {
                    ui.formatMessageFramedWithDivider(profile.ERROR_AGE);
                }
                ;
            } catch (NumberFormatException e) {
                ui.formatMessageFramedWithDivider(MESSAGE_INVALID_POSITIVE_INT_INPUT);
            }
        }
    }

    /**
     * Creates a valid profile gender for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileGender(Profile newProfile) {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_GENDER);
        while (!newProfile.getProfileGender().isValid()) {
            String userInput = ui.getUserInput();
            if (userInput.length() == 1) {
                char genderInput = userInput.charAt(0);
                newProfile.setProfileGender(new Gender(genderInput));
            }
            if (newProfile.getProfileGender().isValid()) {
                //TODO: check if input is male or female and then output the message.
                ui.formatMessageWithTopDivider(
                        getGenderMessage(newProfile.getProfileGender().getGender()));
            } else {
                ui.formatMessageFramedWithDivider(profile.ERROR_GENDER);
            }
        }
    }

    private String getGenderMessage(char gender) {
        return gender == MALE_CHAR ? MESSAGE_MALE : MESSAGE_FEMALE;
    }

    /**
     * Creates a valid profile weight for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileWeight(Profile newProfile) throws ParamMissingException {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_WEIGHT);
        while (!newProfile.getProfileWeight().isValid()) {
            try {
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                double weightInput = Double.parseDouble(userInput);
                newProfile.setProfileWeight(new Weight(weightInput));
                if (newProfile.getProfileWeight().isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_WEIGHT,
                                    newProfile.getProfileWeight().getWeight()));
                } else {
                    ui.formatMessageFramedWithDivider(profile.ERROR_WEIGHT);
                }
            } catch (NumberFormatException e) {
                ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive number");
            }
        }
    }

    /**
     * Creates a valid profile height for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileHeight(Profile newProfile) throws ParamMissingException {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_HEIGHT);
        while (!newProfile.getProfileHeight().isValid()) {
            try {
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                double heightInput = Double.parseDouble(userInput);
                newProfile.setProfileHeight(new Height(heightInput));
                if (newProfile.getProfileHeight().isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_HEIGHT,
                                    newProfile.getProfileHeight().getHeight()));
                } else {
                    ui.formatMessageFramedWithDivider(profile.ERROR_HEIGHT);
                }
            } catch (NumberFormatException e) {
                ui.formatMessageFramedWithDivider(MESSAGE_INVALID_POSITIVE_DOUBLE_INPUT);
            }
        }
    }

    /**
     * Creates a valid profile name for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    private void createNewProfileName(Profile newProfile) throws ParamMissingException {
        ui.formatMessageWithBottomDivider(MESSAGE_INTRO_NAME);
        while (!newProfile.getProfileName().isValid()) {
            String userInput = ui.getUserInput();
            checkEmptyUserInput(userInput);
            newProfile.setProfileName(new Name(userInput));
            if (newProfile.getProfileName().isValid()) {
                ui.formatMessageWithTopDivider(
                        String.format(MESSAGE_NAME,
                                newProfile.getProfileName().getName()));
            } else {
                ui.formatMessageFramedWithDivider(profile.ERROR_NAME);
            }
        }
        assert newProfile.getProfileName().isValid() : " name is valid";
    }

    /**
     * Checks if user input is empty.
     *
     * @param userInput input from the user.
     * @throws ParamMissingException if input length is 0 (missing).
     */
    private void checkEmptyUserInput(String userInput) throws ParamMissingException {
        if (userInput.length() == 0) {
            throw new ParamMissingException(MESSAGE_EMPTY_INPUT);
        }
    }
}
