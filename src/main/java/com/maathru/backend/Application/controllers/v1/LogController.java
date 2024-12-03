package com.maathru.backend.Application.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    private static final String LOG_FILE_PATH = "logs/received-logs.log";

    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLogs() {
        try {
            List<String> logs = Files.readAllLines(Paths.get(LOG_FILE_PATH));
            return ResponseEntity.ok(logs);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error reading log file: " + e.getMessage()));
        }
    }
}
