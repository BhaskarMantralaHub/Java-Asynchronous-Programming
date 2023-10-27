package org.bhaskarmantralahub.fork_join;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;
import static org.bhaskarmantralahub.WaitUtil.stopWatch;

public class StringTransformation {

    private static final List<String> input = List.of("Bhaskar", "Mantrala", "Hub");

    private static List<String> getListOfStringsWithLen() {
        stopWatch.start();
        List<String> stringList = input.stream().map(str -> {
            fixedPause(1000);
            return str.length() + "-" + str;
        }).collect(Collectors.toList());
        stopWatch.stop();
        System.out.println("Time taken for String transformation " + stopWatch.getTime());
        return stringList;
    }

    public static void main(String[] args) {
        getListOfStringsWithLen().forEach(System.out::println); //Time taken for String transformation 3032
    }


}
