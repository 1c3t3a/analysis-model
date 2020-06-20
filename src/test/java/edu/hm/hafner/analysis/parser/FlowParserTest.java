package edu.hm.hafner.analysis.parser;

import edu.hm.hafner.analysis.AbstractParserTest;
import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.Report;
import edu.hm.hafner.analysis.Severity;
import edu.hm.hafner.analysis.assertions.SoftAssertions;

/**
 * Tests the class {@link FlowParser}.
 */
class FlowParserTest extends AbstractParserTest {
    private static final String CATEGORY = DEFAULT_CATEGORY;

    protected FlowParserTest() {
        super("flow.json");
    }

    @Override
    protected IssueParser createParser() {
        return new FlowParser();
    }

    @Override
    protected void assertThatIssuesArePresent(Report report, SoftAssertions softly) {
        softly.assertThat(report).hasSize(1);
        softly.assertThat(report.get(0))
                .hasFileName("src/app.js")
                .hasSeverity(Severity.ERROR)
                .hasCategory(CATEGORY)
                .hasType("infer")
                .hasMessage("Cannot call `server.serve` with `80` bound to `port` because number [1] is incompatible with string [2]. [incompatible-call]")
                .hasLineStart(12)
                .hasLineEnd(12)
                .hasColumnStart(18)
                .hasColumnEnd(19);
    }
}