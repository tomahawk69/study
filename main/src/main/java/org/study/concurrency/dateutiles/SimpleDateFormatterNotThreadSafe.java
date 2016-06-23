package org.study.concurrency.dateutiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatterNotThreadSafe extends AbstractDateTimeFormatter {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected Date parseDate(String dateStr) {
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String parseStr(Date date) {
        return formatter.format(date);
    }
}
