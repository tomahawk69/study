package org.study.xml;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yur on 28.10.2015.
 */
@XmlRootElement(name = "Row")
@XmlAccessorType(XmlAccessType.NONE)
public class HBaseRow {

    private UUID key;
    private Map<String, HBaseCell> cells = new HashMap<>();

    public String getKey() {
        return key.toString();
    }

    @XmlAttribute
    public void setKey(String key) {
        this.key = UUID.fromString(HBaseSerializerUtils.decodeString(key));
    }

    @XmlElement(name = "Cell")
    public void setCell(HBaseCell cell) {
        cells.put(cell.getColumn(), cell);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HBaseRow hBaseRow = (HBaseRow) o;

        if (!key.equals(hBaseRow.key)) return false;
        return cells.equals(hBaseRow.cells);

    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "HBaseRow{" +
                "key=" + key +
                ", cells=" + cells +
                '}';
    }
}
