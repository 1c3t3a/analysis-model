package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.gendarme.GendarmeParser;

/**
 * A Descriptor for the Gendarme warnings.
 *
 * @author Lorenz Munsch
 */
class GendarmeDescriptor extends ParserDescriptor {
    private static final String ID = "gendarme";
    private static final String NAME = "Gendarme";

    GendarmeDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new GendarmeParser();
    }
}
