package com.talkdesk.api.service;

import com.talkdesk.api.exception.FileParserException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.talkdesk.api.domain.UtilTest.getPathFromFile;
import static org.junit.Assert.*;

public class TalkDeskServiceTest {

    private final TalkDeskService service = new TalkDeskService();

    @Test
    public void testCalculate() throws FileParserException {
        String path = getPathFromFile("calls.txt");
        BigDecimal expectedValue = BigDecimal.valueOf(0.51);
        BigDecimal actualValue = service.calculateBill(path);
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = FileParserException.class)
    public void testPathErrorCalculate() throws FileParserException {
        service.calculateBill("some text");
    }

    @Test(expected = FileParserException.class)
    public void testFileErrorCalculate() throws FileParserException {
        String path = getPathFromFile("callsError.txt");
        service.calculateBill(path);
    }

}