package com.talkdesk.api.domain;

import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class CallRecordTest {

    @Test
    public void testDurationMinutesHighBound() {
        long expectedDuration = 1;
        CallRecord callRecord = new CallRecord("some number", "some number", "00:00:00", "00:00:59");
        assertEquals(expectedDuration, callRecord.getDurationInMinutes());
    }

    @Test
    public void testDurationMinutesLowBound() {
        long expectedDuration = 1;
        CallRecord callRecord = new CallRecord("some number", "some number", "00:00:00", "00:00:01");
        assertEquals(expectedDuration, callRecord.getDurationInMinutes());
    }

    @Test
    public void testDurationMinutes() {
        long expectedDuration = 5;
        CallRecord callRecord = new CallRecord("some number", "some number", "00:00:00", "00:04:59");
        assertEquals(expectedDuration, callRecord.getDurationInMinutes());
    }

    @Test(expected = DateTimeParseException.class)
    public void testTimeFormatError() {
        new CallRecord("some number", "some number", "25:00:00", "00:04:59");
    }
}