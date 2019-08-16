package com.talkdesk.api.domain;

import com.talkdesk.api.exception.FileParserException;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the information of calls file, parsing the file and resulting in a List of Call Record
 */
@Getter
public class FileInformation {

    private final List<CallRecord> callRecords;

    public FileInformation(String filePath) throws FileParserException {
        try {
            callRecords = parse(filePath);
        } catch (Exception e) {
            throw new FileParserException(e.getMessage());
        }
    }

    /**
     * Parse the file, reading line by line and storage in a List of Call Record.
     * @param filePath path of file
     * @return the List of Call Records
     * @throws IOException in case of error reading the file.
     */
    private List<CallRecord> parse(String filePath) throws IOException {
        List<String> linesFromFile = Files.readAllLines(Paths.get(filePath));
        return linesFromFile
                .stream()
                .map(line -> {
                    String[] values = line.split(";");
                    return new CallRecord(values[2].trim(), values[3].trim(), values[0].trim(), values[1].trim());
                })
                .collect(Collectors.toList());
    }
}
