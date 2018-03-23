package io.mart.util;

import cucumber.api.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogCollector {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    static final Pattern rowPattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.\\d{3}) (.*)", Pattern.DOTALL);


    public void recordExternalServicesLogs(Scenario scenario,
                                           LocalDateTime scenarioStartTime,
                                           Set<String> tags) {
        List<String> serviceLinks = getServiceLinks(tags);
        for (String link : serviceLinks) {
            String logsPrefix = String.format("logs from: (%s) after %s", link, scenarioStartTime);
            try {
                String s = org.apache.http.client.fluent.Request.Get(link)
                        .connectTimeout(4000)
                        .execute()
                        .returnContent()
                        .asString();

                String extracted = parseLogs(s, scenarioStartTime);

                if (StringUtils.isBlank(extracted)) {
                    scenario.write(logsPrefix + " - missing");
                } else {
                    scenario.write(logsPrefix + " - are");
                    scenario.write(extracted);
                }
            } catch (Exception e) {
                scenario.write(logsPrefix + " - cannot read logs because " + e);
            }
        }
    }

    private List<String> getServiceLinks(Set<String> tags) {
        return Arrays.asList("http://localhost:8080/logfile");
    }

    private String parseLogs(String logFile, LocalDateTime scenarioStartTime) {
        String[] lines = logFile.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher m = rowPattern.matcher(line);
            if (m.matches()) {
                //we begin new line and detect time of this event
                String dateTimePart = m.group(1);
                LocalDateTime timeOnServerInServerTz = LocalDateTime.parse(dateTimePart, formatter);
                if (timeOnServerInServerTz.isAfter(scenarioStartTime)) {
                    //get all logs from here to end as current state
                    return StringUtils.join(lines, "\n", i, lines.length);
                }
            }
        }
        return null;
    }
}
