package org.study.xml;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yur on 28.10.2015.
 */
@XmlRootElement(name = "CellSet")
@XmlAccessorType(XmlAccessType.NONE)
public class HBaseTable {
    private List<HBaseRow> rows = new LinkedList<>();

    public List<HBaseRow> getRows() {
        return rows;
    }

    @XmlElement(name = "Row")
    public void setRow(HBaseRow row) {
        rows.add(row);
    }

    @Override
    public String toString() {
        return "HBaseTable{" +
                "rows=" + rows +
                '}';
    }
}
