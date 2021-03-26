package edu.hm.hafner.analysis.parser;

import java.util.Optional;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import edu.hm.hafner.analysis.Issue;
import edu.hm.hafner.analysis.IssueBuilder;
import edu.hm.hafner.analysis.LookaheadParser;
import edu.hm.hafner.analysis.Severity;
import edu.hm.hafner.util.LookaheadStream;

/**
 * A parser for gcc 4.x linker warnings.
 *
 * @author Frederic Chateau
 */
public class Gcc4LinkerParser extends LookaheadParser {
    private static final long serialVersionUID = -2792019431810134790L;

    /** A GCC error. */
    static final String WARNING_CATEGORY = "GCC4 Linker Error";
    /** Pattern of gcc 4 linker warnings. */
    private static final String LINKER_WARNING_PATTERN = "^(?:(.+?)(?:(?::(?:(\\d+):)? (undefined reference to .*))|"
            + "(?::?\\(\\.\\w+\\+0x[0-9a-fA-F]+\\)): (?:(warning): )?(.*))|.*/ld(?:\\.exe)?: (?:(warning): )?(.*))$";

    /**
     * Creates a new instance of {@link Gcc4LinkerParser}.
     */
    public Gcc4LinkerParser() {
        super(LINKER_WARNING_PATTERN);
    }

    @Override
    protected Optional<Issue> createIssue(final Matcher matcher, final LookaheadStream lookahead,
            final IssueBuilder builder) {
        Severity priority;

        String message;
        if (StringUtils.isNotBlank(matcher.group(7))) {
            // link error in ld
            if (StringUtils.equalsIgnoreCase(matcher.group(6), "warning")) {
                priority = Severity.WARNING_NORMAL;
            }
            else {
                priority = Severity.WARNING_HIGH;
            }
            message = matcher.group(7);
        }
        else {
            // link error
            if (StringUtils.isNotBlank(matcher.group(3))) {
                // error of type "undefined reference..."
                message = matcher.group(3);
                priority = Severity.WARNING_HIGH;
            }
            else {
                // generic linker error with reference to the binary section and
                // offset
                if (StringUtils.equalsIgnoreCase(matcher.group(4), "warning")) {
                    priority = Severity.WARNING_NORMAL;
                }
                else {
                    priority = Severity.WARNING_HIGH;
                }
                message = matcher.group(5);
                if (StringUtils.endsWith(message, ":")) {
                    return Optional.empty();
                }
            }
        }

        return builder.setFileName(StringUtils.defaultString(matcher.group(1)))
                .setLineStart(matcher.group(2))
                .setCategory(WARNING_CATEGORY)
                .setMessage(message)
                .setSeverity(priority)
                .buildOptional();
    }
}

