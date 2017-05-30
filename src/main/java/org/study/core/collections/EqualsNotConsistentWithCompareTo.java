package org.study.core.collections;

import java.math.BigDecimal;

/*
    Inconsistency of compareTo relating to equals
    Consistence of compareTo with equals "is strongly recommended (but not required)
 */
public class EqualsNotConsistentWithCompareTo {

    public static void main(String[] args) {
        BigDecimal big1 = new BigDecimal("4.0");
        BigDecimal big2 = new BigDecimal("4.00");
        System.out.println("BigDecimal compareTo is " + big1.compareTo(big2));
        System.out.println("BigDecimal equals is " + big1.equals(big2));


    }
}
