package com.shazel.runanalyzer;

public enum EventType {
    SUCCESS,
    FAIL,
    OTHER;

    public static EventType fromString(String raw){
        if (raw == null) return OTHER;

        String s = raw.trim().toUpperCase();
        if (s.equals("FAIL")) return FAIL;
        if (s.equals("SUCCESS") || s.equals("OK") || s.equals("PB")) {
            return SUCCESS;
        }

        return OTHER;

        }

        public boolean countsAsSuccess() {
        return this == SUCCESS;
    }

    public boolean countAsFailure() {
        return this == FAIL;
    }
}
