package com.codecool.projectq.projectqbackend.service;

import java.time.Instant;

public class TimeUtil {
    static final long MINUTE = 60000; // todo 1000 for debug mode

    static long getNow() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }
}
