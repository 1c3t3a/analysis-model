package edu.hm.hafner.analysis.parser;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import edu.hm.hafner.analysis.Issue;
import edu.hm.hafner.analysis.IssueBuilder;
import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.ParsingCanceledException;
import edu.hm.hafner.analysis.ParsingException;
import edu.hm.hafner.analysis.ReaderFactory;
import edu.hm.hafner.analysis.Report;
import edu.hm.hafner.analysis.Severity;

/**
 * <p>
 * Parser for reports of aquasec trivy container vulnerability scanner.
 * </p>
 * <p>
 * <strong>Usage: </strong>trivy image -f json -o results.json golang:1.12-alpine
 * </p>
 *
 * @author Thomas Fürer - tfuerer.javanet@gmail.com
 */
public class TrivyParser extends IssueParser {
    private static final long serialVersionUID = 1L;

    @Override
    public Report parse(final ReaderFactory readerFactory) throws ParsingException, ParsingCanceledException {
        final Report report = new Report();

        try (Reader reader = readerFactory.create()) {
            final JSONArray jsonReport = (JSONArray)new JSONTokener(reader).nextValue();

            final JSONArray vulnatbilites = ((JSONObject)jsonReport.get(0)).getJSONArray("Vulnerabilities");
            for (Object vulnatbility : vulnatbilites) {
                report.add(convertToIssue((JSONObject)vulnatbility));
            }
        }
        catch (IOException e) {
            throw new ParsingException(e);
        }

        return report;
    }

    private Issue convertToIssue(final JSONObject vulneratbility) {
        return new IssueBuilder().setFileName(vulneratbility.getString("PkgName"))
                .setCategory(vulneratbility.getString("SeveritySource"))
                .setSeverity(mapSeverity(vulneratbility.getString("Severity")))
                .setType(vulneratbility.getString("VulnerabilityID"))
                .setMessage(vulneratbility.optString("Title", "UNKNOWN"))
                .setDescription(formatDescription(vulneratbility))
                .build();
    }

    private Severity mapSeverity(final String string) {
        if ("low".equalsIgnoreCase(string)) {
            return Severity.WARNING_LOW;
        }
        else if ("medium".equalsIgnoreCase(string)) {
            return Severity.WARNING_NORMAL;
        }
        else if ("high".equalsIgnoreCase(string) || "critcal".equalsIgnoreCase(string)) {
            return Severity.WARNING_HIGH;
        }
        else {
            return Severity.WARNING_HIGH;
        }
    }

    private String formatDescription(final JSONObject vulneratbility) {
        return new StringBuilder().append(MessageFormat.format(
                "<p><div><b>File</b>: {0}</div><div><b>Installed Version:</b> {1}</div><div><b>Fixed Version:</b> {2}</div><div><b>Severity:</b> {3}</div>",
                vulneratbility.getString("PkgName"), vulneratbility.getString("InstalledVersion"),
                vulneratbility.getString("FixedVersion"), vulneratbility.getString("Severity")))
                .append("<p>")
                .append(vulneratbility.getString("Description"))
                .append("</p>")
                .toString();
    }

}
