package org.study.concurrency.dateutiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleDateFormatThreadSafe extends AbstractDateTimeFormatter {
    private static AtomicInteger formatterInstancesCount = new AtomicInteger(0);

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal() {
        @Override
        protected SimpleDateFormat initialValue() {
            formatterInstancesCount.incrementAndGet();
            return new SimpleDateFormat("yyyy-mm-dd");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(new SimpleDateFormatThreadSafe()).start();
        }
    }

    @Override
    protected Date parseDate(String dateStr) {
        try {
            return threadLocal.get().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String parseStr(Date date) {
        return threadLocal.get().format(date);
    }

    public static Integer getFormatterInstancesCount() {
        return formatterInstancesCount.get();
    }
}
