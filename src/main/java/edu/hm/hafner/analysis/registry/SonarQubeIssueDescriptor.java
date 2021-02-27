package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.SonarQubeIssuesParser;

/**
 * A Descriptor for the Sonar Qube Issue parser.
 *
 * @author Lorenz Munsch
 */
class SonarQubeIssueDescriptor extends ParserDescriptor {
    private static final String ID = "sonar_Issue";
    private static final String NAME = "SonarQubeIssue";

    SonarQubeIssueDescriptor() {
        super(ID, NAME);
    }

    @Override
    public edu.hm.hafner.analysis.IssueParser createParser() {
        return new SonarQubeIssuesParser();
    }
}
