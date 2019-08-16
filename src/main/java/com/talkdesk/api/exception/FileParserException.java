package com.talkdesk.api.exception;

/**
 * Exception used in some error parsing the file
 */
public class FileParserException extends Exception {
    public FileParserException(String message) {
        super(message);
    }
}
