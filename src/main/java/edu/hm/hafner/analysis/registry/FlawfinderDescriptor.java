package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.parser.FlawfinderParser;
import edu.hm.hafner.analysis.parser.violations.Flake8Adapter;

/**
 * A descriptor for Flawfinder.
 *
 * @author Lorenz Munsch
 */
class FlawfinderDescriptor extends ParserDescriptor {
    private static final String ID = "flawfinder";
    private static final String NAME = "FlawFinder";

    FlawfinderDescriptor() {
        super(ID, NAME);
    }

    @Override
    public IssueParser createParser() {
        return new FlawfinderParser();
    }

    @Override
    public String getHelp() {
        return "Use commandline <code>flawfinder -S</code>.";
    }

    @Override
    public String getUrl() {
        return "https://dwheeler.com/flawfinder/";
    }
}
