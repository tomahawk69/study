package org.study.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * Created by yur on 11.11.2015.
 */
public class DateUtils {

    public static Long dateDiff(LocalDate dateFrom, LocalDate dateTo) {
        return dateTo.getLong(ChronoField.EPOCH_DAY) - dateFrom.getLong(ChronoField.EPOCH_DAY);
    }
}
