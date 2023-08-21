package com.fmsp.fonyou.config;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class DateConversionUtil {

    private static final String UTC_COLOMBIA = "UTC-5";

    public Instant convertInstantToUtcOffset(Instant instant, String utcStudent) {
        String utcStudentInt = utcStudent.substring(3);
        String utcColtInt = UTC_COLOMBIA.substring(3);

        var hourDiff = (Integer.parseInt(utcColtInt)) - (Integer.parseInt(utcStudentInt));
        Duration subtractedDuration = Duration.ofHours(hourDiff);

        return instant.minus(subtractedDuration);
    }

}
