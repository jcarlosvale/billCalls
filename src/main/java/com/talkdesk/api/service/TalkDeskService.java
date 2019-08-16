package com.talkdesk.api.service;

import com.talkdesk.api.domain.PhoneNumber;
import com.talkdesk.api.domain.CallRecord;
import com.talkdesk.api.domain.FileInformation;
import com.talkdesk.api.exception.FileParserException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Main class to calculate the bill.
 * - First parse the file generating a FileInformation
 * - Using the FileInformation that provide the List of Call Records, apply the defined rules and calculate the value.
 */
public class TalkDeskService {

    /**
     * Main method to generate the FileInformation and proceed to calculate the bill
     * @param filePath path of File
     * @return the value of calculation
     * @throws FileParserException in case of error parsing the file
     */
    public BigDecimal calculateBill(String filePath) throws FileParserException {
        FileInformation fileInformation = new FileInformation(filePath);
        return calculateBill(fileInformation);
    }

    /**
     * This method apply the rules:
     *  - The first 5 minutes of each call are billed at 5 cents per minute
     *  - The remainder of the call is billed at 2 cents per minute
     *  - The caller with the highest total call duration of the day will not be charged
     *  (i.e., the caller that has the highest total call duration among all of its calls)
     *
     *  Strategy:
     *  - Generate a Map with Key = PhoneNumber Caller and Value = total of the call's duration
     *  - Eliminate the highest call duration among all of its calls
     *  - Apply the currency values for the other calls.
     * @param fileInformation  the information of File - list of Call Records.
     * @return the calculated value
     */
    private BigDecimal calculateBill(FileInformation fileInformation) {
        List<CallRecord> records = fileInformation.getCallRecords();
        Map<PhoneNumber, Long> mapOfDurations = new HashMap<>();
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
