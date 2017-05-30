package org.study.core.object;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yur on 28.07.2015.
 */
public class CloneObjectTest {

    @Test
    public void testClone() throws Exception {
        CloneObject testSource = new CloneObject();
        CloneObject result = testSource.cloneObject();
        assertEquals(testSource, result);
    }
}