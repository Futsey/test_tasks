package fly_path_percentile;

import fly_path_percentile.model.Ticket;
import fly_path_percentile.service.reader.JSONReader;
import fly_path_percentile.service.reader.Reader;
import fly_path_percentile.util.PercentilUtil;

import java.util.List;

import static fly_path_percentile.util.PercentilUtil.*;

public class Main {

    static String avgFlightTime = """
            Cреднее время полета между городами Владивосток и Тель-Авив:         
            """;

    static String percentileFlightTime = """
            90-й процентиль времени полета между городами  Владивосток и Тель-Авив:         
            """;

    public static void main(String[] args) {
        Reader<Ticket> jsonReader = new JSONReader();
        String path = "C:\\Users\\Fut\\IdeaProjects\\fly_path_percentil\\src\\main\\resources\\json_files\\tickets.json";
        List<Ticket> ticketList = jsonReader.read(path);

        List<Long> latencyList = getLatencyList(ticketList);
        System.out.println(avgFlightTime
                .concat(PercentilUtil.showInHours((long) avg(latencyList))));
        System.out.println(System.lineSeparator());
        System.out.println(percentileFlightTime
                .concat(PercentilUtil.showInHours(percentile(latencyList, 90))));
    }
}
