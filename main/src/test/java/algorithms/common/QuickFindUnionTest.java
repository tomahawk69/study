package algorithms.common;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickFindUnionTest {
    //    private static final Logger logger = LogManager.getLogger(QuickFindUnionTest.class);
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    //    private static void logInfo(Description description, String status, long nanos) {
//        String testName = description.getMethodName();
//        logger.info(String.format("Test %s %s, spent %d microseconds",
//                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
//    }
//
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

//    @Rule
//    public Stopwatch stopwatch = new Stopwatch() {
//        @Override
//        protected void succeeded(long nanos, Description description) {
//            logInfo(description, "succeeded", nanos);
//        }
//
//        @Override
//        protected void failed(long nanos, Throwable e, Description description) {
//            logInfo(description, "failed", nanos);
//        }
//
//        @Override
//        protected void finished(long nanos, Description description) {
//            logInfo(description, "finished", nanos);
//        }
//    };

    public void testFind(QuickFindUnion quickFind, int size) throws Exception {
        for (int i = 0; i < size; i++) {
            assertTrue(quickFind.find(i, i));
            for (int j = i + 1; j < size; j++) {
                assertFalse(quickFind.find(i, j));
            }
        }
    }

    public void testUnion(QuickFindUnion quickFind, int size, int... v) throws Exception {
        for (int i = 1; i < v.length; i++) {
            assertFalse(quickFind.find(v[i - 1], v[i]));
        }
        for (int i = 1; i < v.length; i++) {
            quickFind.union(v[0], v[i]);
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                assertTrue(quickFind.find(v[i], v[j]));
            }
        }
        Set<Integer> set = Arrays.stream(v).boxed().collect(Collectors.toSet());
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (set.contains(i) && set.contains(j)) {
                    assertTrue(quickFind.find(i, j));
                } else {
                    assertFalse(quickFind.find(i, j));
                }
            }
        }
    }

    public void testUnionRandom(QuickFindUnion quickFind, int size) throws Exception {
        int v1 = random.nextInt(size);
        int v2 = random.nextInt(size);
        while (v2 == v1) {
            v2 = random.nextInt(size);
        }
        int v3 = random.nextInt(size);
        while (v3 == v1 || v3 == v2) {
            v3 = random.nextInt(size);
        }
        testUnion(quickFind, size, v1, v2, v3);
    }

}
