package org.bhaskarmantralahub;

import org.apache.commons.lang3.time.StopWatch;

public class WaitUtil {

    public static StopWatch stopWatch = new StopWatch();

    public static void fixedPause(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {

        }
    }

}
