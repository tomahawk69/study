package org.study.xml;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.*;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by yur on 28.10.2015.
 */
@XmlElement
@XmlAccessorType(XmlAccessType.NONE)
public class HBaseCell {
    private String column;
    private String value;
    private LocalDateTime dateTime;

    public String getColumn() {
        return column;
    }

    @XmlAttribute
    public void setColumn(String column) {
        this.column = HBaseSerializerUtils.decodeString(column);
    }

    public String getValue() {
        return value;
    }

    @XmlValue
    public void setValue(String value) {
        this.value = HBaseSerializerUtils.decodeString(value);
    }

    public String getTimestamp() {
        return String.valueOf(dateTime.toEpochSecond(ZoneOffset.UTC));
    }

    @XmlAttribute
    public void setTimestamp(String timestamp) {
        Instant fromUnixTimestamp = Instant.ofEpochSecond(Long.decode(timestamp)/1000);
        this.dateTime = LocalDateTime.ofInstant(fromUnixTimestamp, Clock.systemUTC().getZone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HBaseCell hBaseCell = (HBaseCell) o;

        if (!column.equals(hBaseCell.column)) return false;
        if (value != null ? !value.equals(hBaseCell.value) : hBaseCell.value != null) return false;
        return !(dateTime != null ? !dateTime.equals(hBaseCell.dateTime) : hBaseCell.dateTime != null);

    }

    @Override
    public int hashCode() {
        return column.hashCode();
    }

    @Override
    public String toString() {
        return "HBaseCell{" +
                "column='" + column + '\'' +
                ", value='" + value + '\'' +
                ", date=" + dateTime +
                '}';
    }
}