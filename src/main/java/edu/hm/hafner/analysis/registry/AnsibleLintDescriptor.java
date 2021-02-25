package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.parser.AnsibleLintParser;

/**
 * A Descriptor for the AnsibleLint warnings.
 *
 * @author Lorenz Munsch
 */
class AnsibleLintDescriptor extends ParserDescriptor {
    private static final String ID = "ansiblelint";
    private static final String NAME = "Ansible Lint";

    AnsibleLintDescriptor() {
        super(ID, NAME, new AnsibleLintParser());
    }
}
