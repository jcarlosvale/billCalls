package com.talkdesk;

import com.talkdesk.api.service.TalkDeskService;

import java.io.IOException;

public class BillCallsApp {
    public static void main( String[] args ) throws IOException {
        if (args.length != 1) {
            System.out.println("Missing parameters: <input_file_path>");
            return;
        }

        String filePath = args[0];
        TalkDeskService talkDeskService = new TalkDeskService();
        System.out.println(talkDeskService.calculateBill(filePath));
    }
}
