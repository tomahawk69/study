package org.study.core.classes;

/**
 * Created by iovchynnikov on 7/29/2015.
 */
public class ClassTest {
    private Object value;

    public String getValue(Object value) {
        return String.valueOf(value);
    }

    public Integer getValue(String value) {
        return Integer.parseInt(value);
    }
}
