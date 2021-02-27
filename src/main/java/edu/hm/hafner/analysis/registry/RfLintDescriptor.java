package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.RfLintParser;

/**
 * A Descriptor for the Rf Lint parser.
 *
 * @author Lorenz Munsch
 */
class RfLintDescriptor extends ParserDescriptor {
    private static final String ID = "rflint";
    private static final String NAME = "Robot Framework Lint";

    RfLintDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new RfLintParser();
    }
}
