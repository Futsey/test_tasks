package fly_path_percentile.reader.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Percentil {

    public static void main(String[] args) {
        List<Long> latencies = Arrays.asList(3L,6L,7L,8L,8L,9L,10L,13L,15L,16L,20L);
        Collections.sort(latencies);

        System.out.println(percentile(latencies, 25));
        System.out.println(percentile(latencies, 50));
        System.out.println(percentile(latencies, 75));
        System.out.println(percentile(latencies, 90));
    }

    public long flightTime(Long departed, Long arrived) {
        return arrived - departed;
    }

    public static long percentile(List<Long> latencies, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * latencies.size());
        return latencies.get(index - 1);
    }

    public static String showInHours(long time) {
        long seconds = (time / 1000) % 60 ;
        long minutes = (time / (1000*60)) % 60;
        long hours   = (time / (1000*60*60)) % 24;

        return String.format("%d hours %d min %d sec",
                hours,
                minutes,
                seconds
        );
    }
}
