package com.talkdesk.api.domain;

import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a CallRecord with:
 * - PhoneNumber callFrom
 * - PhoneNumber callTo
 * - LocalTime timeOfStart - call start
 * - LocalTime timeOfFinish - call finish
 */
@Data
public class CallRecord {

    private final PhoneNumber callFrom;
    private final PhoneNumber callTo;
    private final LocalTime timeOfStart;
    private final LocalTime timeOfFinish;

    /**
     * Constructor parsing the String values to fields
     * @param phoneNumberFrom origin phone number
     * @param phoneNumberTo destiny phone number
     * @param timeOfStartISOFormat start time in HH:mm:ss
     * @param timeOfFinishISOFormat finish time in HH:mm:ss
     */
    CallRecord(String phoneNumberFrom, String phoneNumberTo, String timeOfStartISOFormat,
                      String timeOfFinishISOFormat) {
        callFrom = new PhoneNumber(phoneNumberFrom);
        callTo = new PhoneNumber(phoneNumberTo);
        timeOfStart = LocalTime.parse(timeOfStartISOFormat, DateTimeFormatter.ISO_TIME);
        timeOfFinish = LocalTime.parse(timeOfFinishISOFormat, DateTimeFormatter.ISO_TIME);
    }

    /**
     * Returns the duration in Minutes. For example: 2min:01s will return 3min
     * @return duration in minutes, between start and end time.
     */
    public long getDurationInMinutes() {
        Duration duration = Duration.between(timeOfStart, timeOfFinish);
        long remainder = duration.toMillis() % 60000;
        long minutes = (remainder != 0) ? duration.toMinutes() + 1 : duration.toMinutes();
        return Math.abs(minutes);
    }

}
