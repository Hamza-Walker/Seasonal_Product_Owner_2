package com.codecool.seasonalproductdiscounter.service.logger;

import java.time.LocalDateTime;

public abstract class LoggerBase implements Logger {


    protected abstract void log(String message, String type);

    public static String createLogEntry(String message, String type) {
        return String.format("[%s] %s: %s", LocalDateTime.now(), type, message);
    }

    @Override
    public void logInfo(String message) {
        log(message, "INFO");
    }

    @Override
    public void logError(String message) {
        log(message, "ERROR");
    }
}
