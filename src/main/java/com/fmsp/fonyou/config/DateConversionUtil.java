package com.fmsp.fonyou.config;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

@Service
public class DateConversionUtil {

    public ZonedDateTime convertInstantToAnotherZone(Instant instant, String targetTimeZone) {
        ZoneId targetZoneId = ZoneId.of(targetTimeZone);
        return instant.atZone(targetZoneId);
    }

    public ZonedDateTime convertInstantToUtcOffset(Instant instant, String utcOffsetHours) {
        String utcOffsetString = utcOffsetHours.substring(3);
        int utcOffset = Integer.parseInt(utcOffsetString);

        ZoneOffset targetOffset = ZoneOffset.ofHours(utcOffset);
        return instant.atOffset(targetOffset).toZonedDateTime();
    }

/*
    public static void main(String[] args) {
        Instant instantInBogota = Instant.now(); // Tu Instant en la zona de Bogotá
        String targetTimeZone = "America/Argentina/Buenos_Aires"; // Zona horaria de Argentina

        DateConversionUtil conversionService = new DateConversionUtil();
        ZonedDateTime zonedDateTimeInArgentina = conversionService.convertInstantToAnotherZone(instantInBogota, targetTimeZone);

        System.out.println("Instant in Bogotá: " + instantInBogota);
        System.out.println("Converted ZonedDateTime in Argentina: " + zonedDateTimeInArgentina);
    }
*/


  /*  public static void main(String[] args) {
        Instant instantInBogota = Instant.now(); // Tu Instant en la zona de Bogotá
        int targetUtcOffsetHours = -3; // UTC-3 para Argentina

        DateConversionUtil conversionService = new DateConversionUtil();
        ZonedDateTime zonedDateTimeInArgentina = conversionService.convertInstantToUtcOffset(instantInBogota, targetUtcOffsetHours);

        System.out.println("Instant in Bogotá: " + instantInBogota);
        System.out.println("Converted ZonedDateTime in UTC-3: " + zonedDateTimeInArgentina);
    }*/
}
