package com.talkdesk.api.domain;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FileInformation {

    private final List<CallRecord> callRecords;

    public FileInformation(String filePath) throws IOException {
        callRecords = parse(filePath);
    }

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
