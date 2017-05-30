package org.study.core.generators;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.study.core.generators.SparseIterators.iteratorSparse;

public class BigGenerator {

    // iterator
    public static Iterator<BigInteger> getIterator() {
        return new Iterator<BigInteger>() {

            private BigInteger next = BigInteger.ONE;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public BigInteger next() {
                final BigInteger result = next;
                next = next.add(BigInteger.ONE);
                return result;
            }
        };
    }

    public static Iterator<BigInteger> sparseIterator(int sparsity) {
        if (sparsity == 1) {
            throw new IllegalArgumentException("sparsity of 1 is not supported");
        }
        return Stream.generate(getIterator()::next).filter((x) -> x.bitCount() % sparsity == 0).iterator();
    }

    // methods

    public static Iterator<BigInteger> sampleAfter(Iterator<BigInteger> iterator, int after, int sampleSize) {
        Object t = Stream.generate(iterator::next).skip(after).limit(sampleSize).iterator();
        return Stream.generate(iterator::next).skip(after).limit(sampleSize).iterator();
    }

    public static BigInteger valueAt(Iterator<BigInteger> iterator, int position) {
        return Stream.generate(iterator::next).skip(position).limit(1).findFirst().get();
    }

    public static Iterator<BigInteger> mergeIterators(List<Iterator<BigInteger>> iterators) {
        Iterator<BigInteger> result = new Iterator<BigInteger>() {
            final private Queue<Iterator<BigInteger>> queue = new LinkedList<>(iterators);

            @Override
            public boolean hasNext() {
                while (!queue.isEmpty()) {
                    if (queue.peek().hasNext()) {
                        return true;
                    }
                    queue.poll();
                }
                return false;
            }

            @Override
            public BigInteger next() {
                Iterator<BigInteger> iterator = queue.poll();
                BigInteger result = iterator.next();
                queue.offer(iterator);
                return result;
            }
        };
        return result;
    }

    public static Iterator<BigInteger> mergeIteratorsWithSort(List<Iterator<BigInteger>> iterators) {

        Iterator<BigInteger> result = new Iterator<BigInteger>() {
            final private List<Iterator<BigInteger>> list = new ArrayList<>(iterators);
            final private Map<Integer, BigInteger> values = IntStream.range(0, iterators.size())
                    .boxed()
                    .filter(i -> iterators.get(i).hasNext())
                    .collect(Collectors.toMap(i -> i, i -> iterators.get(i).next()));

            @Override
            public boolean hasNext() {
                return !values.isEmpty();
            }

            @Override
            public BigInteger next() {
                final Optional<Map.Entry<Integer, BigInteger>> opt = values.entrySet().stream().sorted(Map.Entry.comparingByValue()).findFirst();
                if (!opt.isPresent()) {
                    throw new RuntimeException("Smth wrong; stream doesn't work");
                }
                Map.Entry<Integer, BigInteger> entry = opt.get();
                if (list.get(entry.getKey()).hasNext()) {
                    values.put(entry.getKey(), list.get(entry.getKey()).next());
                } else {
                    values.remove(entry.getKey());
                }
                return entry.getValue();
            }
        };
        return result;
    }

    public static double approximateSparsity(int sparsity, int extent) {
        return extent / valueAt(iteratorSparse(sparsity), extent - 1).doubleValue();
    }

    public static Map<Integer, Future<Double>> approximatesFor(int sparsityMin, int sparsityMax, int extent) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Map<Integer, Future<Double>> result = IntStream.range(sparsityMin, sparsityMax + 1)
                .boxed()
                .collect(Collectors.toMap(key -> key, value -> executorService.submit(() -> approximateSparsity(value, extent)),
                        (key, value) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new));
        executorService.shutdown();
        try {
            while (!executorService.isTerminated())
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // throw exception
        }
        return result;
    }

    public static void main(String[] args) {
        Iterator<BigInteger> iterator = sampleAfter(getIterator(), 1, 2);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
