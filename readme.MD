# Asynchronous Programming in Java

* Synchronous 
* Threads
* Executor Service using Thread pool (Task parallelism)
* Fork/Join Framework (Extension of Executor Service - Data Parallelism)
  * Uses Fork Join Pool
  * Work stealing
  * Used before Java 7
* Streams API

# Documentation
[Parallel Asynchronous Programming](src%2Fmain%2Fresources%2Fparallel-asynchronous-programming-java.pdf)

# Parallel Stream
* arrays, ArrayLists, and IntStreams are good sources for parallel streams, while LinkedLists, Streams from IO sources, and stateful operations are not.
* Expensive because ThreadPool size cannot be controlled and probably current thread can also be used
  * it uses JVM’s common fork-join thread pool: ForkJoinPool.common()
  * And if we execute long-running tasks on a parallel stream, there is a high probability that it will use all threads in the common thread pool. Consequently, it will block all other parallel stream operations in the application.


```Java
stopWatch.start();
        IntStream.rangeClosed(1, 1_000_000).parallel().reduce(0, Integer::sum);
        stopWatch.stop();
        System.out.println("\ntestWith parallel Streams response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));
```
#### Output: testWith parallel Streams response time in ms is 141

## Workaround (Custom ForkJoinPool)

```Java
stopWatch.start();
Callable<Integer> task1 = () -> IntStream.rangeClosed(1, 1_000_000).parallel().reduce(0, Integer::sum);
ForkJoinPool forkJoinPool1 = new ForkJoinPool(availableProcessors); //this will guarantee that current thread won't be used
forkJoinPool1.submit(task).get();
stopWatch.stop();
System.out.println("\ntestWith parallel Streams & Fork join response time in ms is " + stopWatch.getTime(TimeUnit.MILLISECONDS));
```

#### Output: testWith parallel Streams & Fork join response time in ms is 6

# Parallel Stream - Preserve Order

* List - preserves order
* Set - Doesn't preserve order