package com.talkdesk.api.service;

import com.talkdesk.api.domain.Call;
import com.talkdesk.api.domain.CallRecord;
import com.talkdesk.api.domain.FileInformation;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TalkDeskService {

    public BigDecimal calculateBill(String filePath) throws IOException {
        FileInformation fileInformation = new FileInformation(filePath);
        return calculateBill(fileInformation);
    }

    private BigDecimal calculateBill(FileInformation fileInformation) {
        List<CallRecord> records = fileInformation.getCallRecords();
        Map<Call, Long> mapOfDurations = new HashMap<>();
        records.forEach(record -> mapOfDurations.merge(record.getCallFrom(), record.getDurationInMinutes(), Long::sum));
        ArrayList<Long> listOfDurations = new ArrayList<>(mapOfDurations.values());
        Collections.sort(listOfDurations);
        //remove the highest total call
        listOfDurations.remove(listOfDurations.size()-1);
        double cost = 0;
        for (Long durationInMinutes:listOfDurations) {
            if (durationInMinutes <= 5) {
                cost = cost + durationInMinutes * 0.05;
            } else {
                cost = cost + 0.25 + (durationInMinutes - 5) * 0.02;
            }
        }
        return BigDecimal.valueOf(cost).setScale(2, RoundingMode.HALF_UP);
    }

}
