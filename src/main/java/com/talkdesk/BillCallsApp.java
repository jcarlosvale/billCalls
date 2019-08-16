package com.talkdesk;

import com.talkdesk.api.exception.FileParserException;
import com.talkdesk.api.service.TalkDeskService;

import java.io.IOException;

/**
 * Main app that receives the input file path and calculate the cost or value.
 */
public class BillCallsApp {
    public static void main( String[] args ) throws FileParserException {
        if (args.length != 1) {
            System.out.println("Missing parameters: <input_file_path>");
            return;
        }

        String filePath = args[0];
        TalkDeskService talkDeskService = new TalkDeskService();
        System.out.println(talkDeskService.calculateBill(filePath));
    }
}
