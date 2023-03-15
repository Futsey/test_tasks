package fly_path_percentile.reader;

import fly_path_percentile.reader.model.Ticket;
import fly_path_percentile.reader.service.reader.JSONReader;
import fly_path_percentile.reader.service.reader.Reader;
import fly_path_percentile.reader.util.PercentilUtil;

import java.util.List;

import static fly_path_percentile.reader.util.PercentilUtil.getLatencyList;
import static fly_path_percentile.reader.util.PercentilUtil.percentile;

public class Main {

    public static void main(String[] args) {
        Reader<Ticket> jsonReader = new JSONReader();
        List<Ticket> ticketList = jsonReader.read("src/main/resources/json_files/tickets.json");

        List<Long> latencyList = getLatencyList(ticketList);
        System.out.println(PercentilUtil.showInHours(percentile(latencyList, 90)));
    }
}
