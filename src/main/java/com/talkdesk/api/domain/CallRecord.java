package com.talkdesk.api.domain;

import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class CallRecord {

    private final Call callFrom;
    private final Call callTo;
    private final LocalTime timeOfStart;
    private final LocalTime timeOfFinish;

    CallRecord(String phoneNumberFrom, String phoneNumberTo, String timeOfStartISOFormat,
                      String timeOfFinishISOFormat) {
        callFrom = new Call(phoneNumberFrom);
        callTo = new Call(phoneNumberTo);
        timeOfStart = LocalTime.parse(timeOfStartISOFormat, DateTimeFormatter.ISO_TIME);
        timeOfFinish = LocalTime.parse(timeOfFinishISOFormat, DateTimeFormatter.ISO_TIME);
    }

    public long getDurationInMinutes() {
        Duration duration = Duration.between(timeOfStart, timeOfFinish);
        long remainder = duration.toMillis() % 60000;
        long minutes = (remainder != 0) ? duration.toMinutes() + 1 : duration.toMinutes();
        return Math.abs(minutes);
    }

}
