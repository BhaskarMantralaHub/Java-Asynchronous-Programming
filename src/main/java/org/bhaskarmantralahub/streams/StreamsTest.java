package org.bhaskarmantralahub.streams;

import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;

public class StreamsTest {

    @Test
    public void testWithOrthodoxLoop() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> input = List.of("Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala");
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            output.add(transform(input.get(i)));
        }
        stopWatch.stop();

        System.out.println("testWithOrthodoxLoop response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testWithStreams() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> input = List.of("Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala");

        List<String> stringList = input.stream().map(StreamsTest::transform).toList();
        stringList.forEach(System.out::print);
        stopWatch.stop();

        System.out.println("\ntestWithStreams response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS)); //testWithStreams response time in ms is 10589
    }

    @Test
    public void testWithParallelStreams() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> input = List.of("Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala", "Bhaskar", "Sarma", "Mantrala");

        List<String> stringList = input.parallelStream().map(StreamsTest::transform).toList();
        stringList.forEach(System.out::print);
        stopWatch.stop();

        System.out.println("\ntestWithStreams response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS)); //testWithStreams response time in ms is 10589
    }

    @Test
    public void testWithParallelStreamsWithForkJoin() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Callable<Integer> task = () -> IntStream.rangeClosed(1, 1_000_000).reduce(0, Integer::sum);
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors " + availableProcessors);
        ForkJoinPool forkJoinPool = new ForkJoinPool(availableProcessors);
        forkJoinPool.submit(task).get();
        stopWatch.stop();
        System.out.println("\ntestWithStreams response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));

        stopWatch.reset();

        //parallel stream - Expensive
        stopWatch.start();
        Callable<Integer> task1 = () -> IntStream.rangeClosed(1, 1_000_000).parallel().reduce(0, Integer::sum);
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(availableProcessors);
        forkJoinPool1.submit(task).get();
        stopWatch.stop();
        System.out.println("\ntestWith parallel Streams & Fork join response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));


        stopWatch.reset();

        stopWatch.start();
        IntStream.rangeClosed(1, 1_000_000).parallel().reduce(0, Integer::sum);
        stopWatch.stop();
        System.out.println("\ntestWith parallel Streams response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));
    }

    private static String transform(String input) {
        fixedPause(500);
        return "%s %s".formatted(input, input.length());
    }
}
