package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdMatchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.matches("[a-zA-Z]+")) {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (trimmedArgs.matches("A\\d{7}[A-Z]")) {
            StudentId studentId = new StudentId(trimmedArgs);
            return new FindCommand(new StudentIdMatchPredicate(studentId));
        } else {
            throw new ParseException(
                    String.format("Please enter either a student id or a name!\\n%s", FindCommand.MESSAGE_USAGE));
        }

    }
}
