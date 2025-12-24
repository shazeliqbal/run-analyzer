package com.shazel.runanalyzer;

import com.shazel.runanalyzer.EventType;
import com.shazel.runanalyzer.RunEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
- Parses the CSV Log into RunEvent Objects

- Must have the following format per line:
    timestamp, runId, segment, event

    timestamp must be of the long type (ex: milliseconds)
    The event is a "FAIL" for failures; for anything else the program will assume success

 */

public class RunLogParser {

    public List<RunEvent> parse(Path path) {

        List<RunEvent> events = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }


                if (firstLine && line.toLowerCase().startsWith("timestamp")) {
                    firstLine = false;
                    continue;
                }

                firstLine = false;

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }

                long timestamp = Long.parseLong(parts[0].trim());
                String runId = parts[1].trim();
                String segmentId = parts[2].trim();
                EventType eventType = EventType.fromString(parts[3].trim());
                events.add(event);

                RunEvent event = new RunEvent(timestamp, runId, segmentId, eventType);
                events.add(event);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return events;

    }
}
