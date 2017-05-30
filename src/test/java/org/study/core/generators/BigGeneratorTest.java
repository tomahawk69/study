package org.study.core.generators;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class BigGeneratorTest {


    @Test
    public void mergeIterators() throws Exception {

        System.out.println("-- Sample After");
        Iterator<BigInteger> iterator = BigGenerator.sampleAfter(BigGenerator.getIterator(), 1, 10);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("-- Value At");
        System.out.println(BigGenerator.valueAt(BigGenerator.getIterator(), 2));

        System.out.println("-- Simple merge");
        List<Iterator<BigInteger>> iterators = new ArrayList<>();
        iterators.add(BigGenerator.sampleAfter(BigGenerator.getIterator(), 1, 10));
        iterators.add(BigGenerator.sampleAfter(BigGenerator.getIterator(), 3, 7));
//        Iterator<BigInteger> result = BigGenerator.mergeIterators(iterators);
        Iterator<BigInteger> result = BigGenerator.mergeIteratorsWithSort(iterators);
        while (result.hasNext())
            System.out.println(result.next());

        System.out.println("-- approximatesFor");
        Map<Integer, Future<Double>> appr = BigGenerator.approximatesFor(2, 4, 100000);
        for (Map.Entry<Integer, Future<Double>> future : appr.entrySet()) {
            System.out.println(future.getKey() + " = " + future.getValue().get());
        }


    }

}