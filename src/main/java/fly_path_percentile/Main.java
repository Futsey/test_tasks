package fly_path_percentile;

import fly_path_percentile.model.Ticket;
import fly_path_percentile.service.reader.JSONReader;
import fly_path_percentile.service.reader.Reader;
import fly_path_percentile.util.PercentilUtil;

import java.util.ArrayList;
import java.util.List;

import static fly_path_percentile.util.PercentilUtil.getLatencyList;
import static fly_path_percentile.util.PercentilUtil.percentile;

public class Main {
    public static void main(String[] args) {
        Reader<Ticket> jsonReader = new JSONReader();
        String path = "C:\\Users\\Fut\\IdeaProjects\\fly_path_percentil\\src\\main\\resources\\json_files\\tickets.json";
        List<Ticket> ticketList = new ArrayList<>();
        ticketList = jsonReader.read(path);

        List<Long> latencyList = getLatencyList(ticketList);
        System.out.println("Cреднее время полета между городами Владивосток и Тель-Авив: "
                .concat(PercentilUtil.showInHours(percentile(latencyList, 50))));
        System.out.println("90-й процентиль времени полета между городами  Владивосток и Тель-Авив: "
                .concat(PercentilUtil.showInHours(percentile(latencyList, 90))));
    }
}
