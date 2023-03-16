package fly_path_percentile.util;

import fly_path_percentile.model.Ticket;
import fly_path_percentile.service.date_convertor.DateConverter;
import fly_path_percentile.service.date_convertor.StringToLocalDateTimeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PercentilUtil {

    private static DateConverter dateConverter = new StringToLocalDateTimeConverter();
    private static PercentilUtil percentilUtil = new PercentilUtil();
    private static List<Long> latencies = new ArrayList<>();;

    public static long percentile(List<Long> latencies, double percentile) {
        Collections.sort(latencies);
        int index = (int) Math.ceil(percentile / 100.0 * latencies.size());
        return latencies.get(index - 1);
    }

    public static double avg(List<Long> latencies) {
        return latencies.stream().mapToLong(a -> a).average().orElse(0);
    }

    public long flightTime(Long departed, Long arrived) {
        return arrived - departed;
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

    public static List<Long> getLatencyList(List<Ticket> ticketList) {
        String vladivostok = "Asia/Vladivostok";
        String telAviv = "Asia/Tel_Aviv";
        for (Ticket latency : ticketList) {
            Long cityDeparted = dateConverter.convertToLong(dateConverter.parseToLocalDateTime(
                    latency.getDepartureDate(), latency.getDepartureTime()), vladivostok);
            Long cityArrived = dateConverter.convertToLong(dateConverter.parseToLocalDateTime(
                    latency.getArrivalDate(), latency.getArrivalTime()), telAviv);
            latencies.add(percentilUtil.flightTime(cityDeparted, cityArrived));
        }
        return latencies;
    }
}
