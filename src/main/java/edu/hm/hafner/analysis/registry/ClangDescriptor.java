package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.ClangParser;

/**
 * A Descriptor for the Clang parser.
 *
 * @author Lorenz Munsch
 */
class ClangDescriptor extends ParserDescriptor {
    private static final String ID = "clang";
    private static final String NAME = "Clang";

    ClangDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new ClangParser();
    }
}
