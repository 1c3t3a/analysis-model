package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.PreFastParser;

/**
 * A Descriptor for the Pre Fast parser.
 *
 * @author Lorenz Munsch
 */
class PreFastDescriptor extends ParserDescriptor {
    private static final String ID = "prefast";
    private static final String NAME = "PREfast";

    PreFastDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new PreFastParser();
    }
}
