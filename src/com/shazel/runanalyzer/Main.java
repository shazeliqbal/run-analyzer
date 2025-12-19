package com.shazel.runanalyzer;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args){

        /* Defines the file path for the run log. In case someone decides to run from a command line and specify
        this also accounts for that by setting the file path to the first element in args.
         */

        Path csvPath;
        if (args.length > 0) {
            csvPath = Path.of(args[0]);
        } else {
            csvPath = Path.of("data", "run_log.csv");
        }

        /* Prints out the beginning of the program. It involves a header, then verifies the full path (toAbsolute
        Path)
         */

        System.out.println("######## RUN ANALYZER #########");
        System.out.println("Reading the log from " + csvPath.toAbsolutePath());
        System.out.println();

        /*
        Creates a new instance of RunLogParser and uses its parse(...) method to read the CSV file at csvPath.
        The parser reads the file line by line, extracts the four fields (timestamp, runId, segment, event),
        and for each line constructs a RunEvent object. All of these RunEvent objects are collected into
        a List<RunEvent>, which is returned and stored in the 'events' variable.
        */

        RunLogParser parser = new RunLogParser();
        List<RunEvent> events = parser.parse(csvPath);

        /*
        If the events list are empty, tells the user that you may not have put anything in the actual log itself.
         */

        if (events.isEmpty()) {
            System.out.println("No events were detected. Please check to make sure that contents actually exist in the file.");
            return;
        }

        /*
        Creates an analyzer, then feeds it all the parsed run events so it can build up the statistics about the runs
        and create valuable information from it
         */

        RunAnalyzer analyzer = new RunAnalyzer();
        analyzer.ingest(events);

        /*
        After the analyzer has ingested all the parsed RunEvent objects and built its internal data structures, it then
        computes the analytics and prints all the results. This will generally include a summary of all the runs.
         */

        analyzer.computerRecentRuns(10);
        analyzer.printSummary();
        System.out.println();
        analyzer.printTopHrdestSegments(5);
        System.out.println();
        analyzer.printRecentWindowSummary();





    }
}
