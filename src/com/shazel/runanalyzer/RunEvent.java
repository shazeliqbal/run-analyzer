package com.shazel.runanalyzer;

public class RunEvent {

    private final long timestampMillis;
    private final String runId;
    private final String segmentId;
    private final EventType eventType;

    public RunEvent(long timestampMillis, String runId, String segmentId, EventType eventType){
        this.timestampMillis = timestampMillis;
        this.runId = runId;
        this.segmentId = segmentId;
        this.eventType = eventType;
    }

    public long getTimestampMillis(){
        return timestampMillis;
    }

    public String getRunId(){
        return runId;
    }

    public String getSegmentId(){
        return segmentId;
    }

    public EventType getEventType(){
        return eventType;
    }
}
