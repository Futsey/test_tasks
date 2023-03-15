package fly_path_percentile.util;

import fly_path_percentile.model.Ticket;
import fly_path_percentile.service.date_convertor.DateConverter;
import fly_path_percentile.service.date_convertor.StringToLocalDateTimeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PercentilUtil {

    public static long percentile(List<Long> latencies, double percentile) {
        Collections.sort(latencies);
        int index = (int) Math.ceil(percentile / 100.0 * latencies.size());
        return latencies.get(index - 1);
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
        Long cityDeparted;
        Long cityArrived;
        DateConverter dateConverter = new StringToLocalDateTimeConverter();
        List<Long> latencies = new ArrayList<>();
        PercentilUtil percentilUtil = new PercentilUtil();
        for (Ticket latency : ticketList) {
            cityDeparted = dateConverter.convertToLong(dateConverter.parseToLocalDateTime(
                    latency.getDepartureDate(), latency.getDepartureTime()), vladivostok);
            cityArrived = dateConverter.convertToLong(dateConverter.parseToLocalDateTime(
                    latency.getArrivalDate(), latency.getArrivalTime()), telAviv);
            latencies.add(percentilUtil.flightTime(cityDeparted, cityArrived));
        }
        return latencies;
    }
}
