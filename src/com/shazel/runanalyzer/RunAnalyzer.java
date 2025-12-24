package com.shazel.runanalyzer;

import java.util.*;

public class RunAnalyzer {

    private final Map<String, Integer> segmentAttempts = new HashMap<>();
    private final Map<String, Integer> segmentFailures = new HashMap<>();
    private final Map<String, Integer> runFailures = new HashMap<>();

    private final List<RunEvent> allEvents = new ArrayList<>();
    private List<String> recentRuns = new ArrayList<>();

    public void ingest(List<RunEvent> events){
        for (RunEvent event : events){
            allEvents.add(event);

            segmentAttempts.merge(event.getSegmentId(), 1, Integer::sum);

            if(event.getEventType().countsAsFailure()) {
             segmentFailures.merge(event.getSegmentId(), 1, Integer::sum);
             runFailures.merge(event.getRunId(), 1, Integer::sum);
            }
        }
    }

    public void computeRecentRuns(int n){
        LinkedHashSet<String> orderedRuns = new LinkedHashSet<>();
        for (RunEvent event : allEvents) {
            orderedRuns.add(event.getRunId());
        }

        List<String> runs = new ArrayList<>(orderedRuns);
        int start = Math.max(0, runs.size() - n);
        recentRuns = runs.subList(start, runs.size());
    }

    public void printSummary() {
        System.out.println("***SUMMARY***");
        System.out.println("Total events: " + allEvents.size());
        System.out.println("Total runs: " + runFailures.size());
        System.out.println("Total segments: " + segmentAttempts.size());
    }

    public void printTopHardsetSegments(int k){
        System.out.println("TOP " + k + " HARDEST SEGMENTS");

        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(failureRate(b), failureRate(a))
        );

        pq.addAll(segmentAttempts.keySet());

        for (int i = 0;  i < k && !pq.isEmpty(); i++){
            String segment = pq.poll();
            System.out.printf("%s - attempts: %d, failures: %d, fail rate: %.2f%%%n", segment, segmentAttempts.get(segment),
                    segmentFailures.getOrDefault(segment, 0), failureRate(segment));
        }

        public void printRecentWindowSummary(){
            System.out.println("==== ");
        }



    }



}
