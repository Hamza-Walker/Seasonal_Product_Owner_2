package com.codecool.seasonalproductdiscounter.products.Logger;

import com.codecool.seasonalproductdiscounter.service.logger.FileLogger;
import com.codecool.seasonalproductdiscounter.service.logger.LoggerBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codecool.seasonalproductdiscounter.service.logger.LoggerBase.createLogEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTest {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void testFileLogger() throws IOException {
        // Arrange
        String filePath = "test.log";
        FileLogger fileLogger = new FileLogger(filePath);
        String message = "Test log message";
        String expectedTimestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String expectedLogEntry = String.format("[%s] INFO: %s", expectedTimestamp, message);

        // Act
        fileLogger.logInfo(message);

        // Assert
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String actualLogEntry = reader.readLine();
            assertLogEntryEquals(expectedLogEntry, actualLogEntry);
        } finally {
            // Clean up the test file
            deleteFile(filePath);
        }
    }

    @Test
    public void testLoggerBase() {
        // Arrange
        TestLogger loggerBase = new TestLogger();
        String message = "Test log message";
        String expectedTimestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String expectedLogEntry = String.format("[%s] ERROR: %s", expectedTimestamp, message);

        // Act
        loggerBase.logError(message);

        // Assert
        assertLogEntryEquals(expectedLogEntry, loggerBase.lastLogEntry);
    }

    // Custom TestLogger class that extends LoggerBase
    static class TestLogger extends LoggerBase {
        public String lastLogEntry;

        @Override
        protected void log(String message, String type) {
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            lastLogEntry = createLogEntry(message, type).replaceFirst("\\[.*\\]", "[" + timestamp + "]");
        }
    }

    private void assertLogEntryEquals(String expected, String actual) {
        String expectedWithoutMilliseconds = expected.replaceFirst("\\.\\d{1,3}", "");
        String actualWithoutMilliseconds = actual.replaceFirst("\\.\\d{1,6}", "");
        assertEquals(expectedWithoutMilliseconds, actualWithoutMilliseconds, "Log entries do not match.");
    }


    private void deleteFile(String filePath) {
        try {
            if (filePath != null) {
                java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
