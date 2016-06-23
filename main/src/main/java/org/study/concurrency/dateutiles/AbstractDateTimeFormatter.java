package org.study.concurrency.dateutiles;

import java.util.Date;
import java.util.Random;

public abstract class AbstractDateTimeFormatter implements Runnable {
    private final static Random random = new Random();

    @Override
    public void run() {
        try {
            String dateStr = getDateStr();
            Thread.sleep(random.nextInt(10));
            Date date = parseDate(dateStr);
            String dateResult = parseStr(date);
            if (!dateResult.equals(dateStr)) {
                System.out.println(dateStr + " is not equal to " + dateResult);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract Date parseDate(String dateStr);
    protected abstract String parseStr(Date date);

    private String getDateStr() {
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;
        return String.format("2016-%02d-%02d", month , day);
    }
}
