package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.EclipseParser;

/**
 * A Descriptor for the Eclipse  parser.
 *
 * @author Lorenz Munsch
 */
class EclipseDescriptor extends ParserDescriptor {
    private static final String ID = "eclipse";
    private static final String NAME = "Eclipse ECJ";

    EclipseDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new EclipseParser();
    }
}
