package com.maathru.backend.External.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtils {

    public static LocalDateTime adjustTime(LocalDateTime time) {
        return time.plus(Duration.ofHours(5).plusMinutes(30));
    }
}