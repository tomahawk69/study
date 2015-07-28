package org.study.design_patterns.prototype.solution;

import org.junit.Test;
import org.study.design_patterns.prototype.solution.Product;

import static org.junit.Assert.*;

/**
 * Created by yur on 28.07.2015.
 */
public class ProductTest {

    @Test
    public void testClone() throws Exception {
        Product product1 = new Product.Product1Impl();
        Product product2 = new Product.Product2Impl();

        Product result1 = product1.clone();
        Product result2 = product2.clone();

        assertEquals(product1, result1);
        assertEquals(product2, result2);
    }
}