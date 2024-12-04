package com.maathru.backend.Application.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    private static final String LOG_FILE_PATH = "logs/received-logs.log";
    private static final int MAX_LINE_LENGTH = 200;

    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLogs() {
        try {
            List<String> logs = Files.readAllLines(Paths.get(LOG_FILE_PATH));

            // Filter logs by maximum line length
            List<String> filteredLogs = logs.stream()
                    .map(String::trim)
                    .filter(log -> log.length() <= MAX_LINE_LENGTH) // Keep lines within max length
                    .collect(Collectors.toList());

            return ResponseEntity.ok(filteredLogs);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error reading log file: " + e.getMessage()));
        }
    }
}
