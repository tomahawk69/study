package org.study.concurrency.dateutiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormatterThreadSafeJava8 extends AbstractDateTimeFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    @Override
    protected Date parseDate(String dateStr) {
        return Date.from(LocalDateTime.parse(dateStr, formatter).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    protected String parseStr(Date date) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return formatter.format(dateTime);
    }
}
