package com.talkdesk.api.domain;

import com.talkdesk.api.exception.FileParserException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.talkdesk.api.domain.UtilTest.getPathFromFile;
import static org.junit.Assert.assertEquals;

public class FileInformationTest {

    @Test
    public void testParseFile() throws FileParserException {
        String path = getPathFromFile("calls.txt");
        List<CallRecord> expectedCallRecords = new ArrayList<>();
        expectedCallRecords.add(new CallRecord("+351914374373","+351215355312", "09:11:30", "09:15:22" ));
        expectedCallRecords.add(new CallRecord("+351217538222","+351214434422", "15:20:04", "15:23:49" ));
        expectedCallRecords.add(new CallRecord("+351217235554","+351329932233", "16:43:02", "16:50:20" ));
        expectedCallRecords.add(new CallRecord("+351914374373","+351963433432", "17:44:04", "17:49:30" ));
        FileInformation fileInformation = new FileInformation(path);
        List<CallRecord> actualCallRecords = fileInformation.getCallRecords();
        assertEquals(expectedCallRecords, actualCallRecords);
    }

    @Test(expected = FileParserException.class)
    public void testInvalidPath() throws FileParserException {
        new FileInformation("some path");
    }

    @Test(expected = FileParserException.class)
    public void testParseFileError() throws FileParserException {
        String path = getPathFromFile("callsError.txt");
        new FileInformation(path);
    }
}