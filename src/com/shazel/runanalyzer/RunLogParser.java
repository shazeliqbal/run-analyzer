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

}
