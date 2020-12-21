package edu.hm.hafner.analysis.parser;

import java.util.Optional;
import java.util.regex.Matcher;

import edu.hm.hafner.analysis.Issue;
import edu.hm.hafner.analysis.IssueBuilder;
import edu.hm.hafner.analysis.RegexpLineParser;
import edu.hm.hafner.analysis.Severity;

/**
 * A parser for the Diab C++ compiler warnings.
 *
 * @author Yuta Namiki
 */
public class DiabCParser extends RegexpLineParser {
    private static final long serialVersionUID = -1251248150596418456L;

    private static final String DIAB_CPP_WARNING_PATTERN = "^\\s*\"(.*)\"\\s*,\\s*line\\s*(\\d+)\\s*:\\s*"
            + "(info|warning|error|fatal\\serror)\\s*\\((?:dcc|etoa):(\\d+)\\)\\s*:\\s*(.*)$";

    /**
     * Creates a new instance of {@link DiabCParser}.
     */
    public DiabCParser() {
        super(DIAB_CPP_WARNING_PATTERN);
    }

    @Override
    protected Optional<Issue> createIssue(final Matcher matcher, final IssueBuilder builder) {
        return builder.setFileName(matcher.group(1))
                .setLineStart(matcher.group(2))
                .setCategory(matcher.group(4))
                .setMessage(matcher.group(5))
                .setSeverity(mapPriority(matcher))
                .buildOptional();
    }

    private Severity mapPriority(final Matcher matcher) {
        if (equalsIgnoreCase(matcher.group(3), "info")) {
            return Severity.WARNING_LOW;
        }
        else if (equalsIgnoreCase(matcher.group(3), "warning")) {
            return Severity.WARNING_NORMAL;
        }
        return Severity.WARNING_HIGH;
    }
}

