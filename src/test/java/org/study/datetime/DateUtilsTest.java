package org.study.datetime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by yur on 11.11.2015.
 */
public class DateUtilsTest {

    @Test
    public void testDateDiff() throws Exception {
        LocalDate dateFrom = LocalDate.of(2015, 11, 12);
        LocalDate dateTo = LocalDate.of(2016, 5, 7);

        Long expectedResult = 177l;
        Long result = DateUtils.dateDiff(dateFrom, dateTo);
        assertEquals(expectedResult, result);
    }
}