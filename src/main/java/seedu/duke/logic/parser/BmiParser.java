package seedu.duke.logic.parser;

import seedu.duke.logic.commands.CalculateBmiCommand;
import seedu.duke.logic.commands.CalculateBmiWithProfileCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ParamInvalidException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Bmi commands.
 */
public class BmiParser implements Parser {

    protected static final Logger logger = Logger.getLogger(BmiParser.class.getName());

    @Override
    public Command parse(String params) {
        if (params.equals(ParserMessages.EMPTY)) { //no additional parameters, assumed to be bmi from current profile
            return new CalculateBmiWithProfileCommand();
        }
        if (ParserUtils.hasRequiredParams(params, CalculateBmiCommand.EXPECTED_PREFIXES)) {
            if (ParserUtils.hasExtraDelimiters(params, CalculateBmiCommand.EXPECTED_PREFIXES)) {
                return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
            }
            try {
                final double height = ParserUtils.extractHeight(params);
                final double weight = ParserUtils.extractWeight(params);
                return new CalculateBmiCommand(height, weight);
            } catch (ParamInvalidException e) {
                return new InvalidCommand(e.getMessage());
            }
        } else {
            logger.log(Level.FINE, "Detected invalid input parameters for BMI calculation.");
            return new InvalidCommand(CalculateBmiCommand.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
