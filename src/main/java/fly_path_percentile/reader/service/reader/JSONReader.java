package fly_path_percentile.reader.service.reader;

import com.google.gson.*;
import fly_path_percentile.reader.model.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONReader implements Reader<Ticket> {

    private Gson gson = new Gson();
    private static final Logger LOG = LoggerFactory.getLogger(JSONReader.class.getName());

    @Override
    public List<Ticket> read(String path) {
        File inputJson = new File(path);
        List<Ticket> ticketList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(inputJson));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("tickets").getAsJsonArray();

            for (JsonElement ticket : jsonArray) {
                JsonObject tmpJsonObj = ticket.getAsJsonObject();
                Ticket tmp = Ticket.builder()
                        .origin(tmpJsonObj.get("origin").getAsString())
                        .originName(tmpJsonObj.get("origin_name").getAsString())
                        .destination(tmpJsonObj.get("destination_name").getAsString())
                        .departureDate(tmpJsonObj.get("departure_date").getAsString())
                        .departureTime(tmpJsonObj.get("departure_time").getAsString())
                        .arrivalDate(tmpJsonObj.get("arrival_date").getAsString())
                        .arrivalTime(tmpJsonObj.get("arrival_time").getAsString())
                        .carrier(tmpJsonObj.get("carrier").getAsString())
                        .stops(tmpJsonObj.get("stops").getAsInt())
                        .price(tmpJsonObj.get("price").getAsDouble())
                        .build();
                ticketList.add(tmp);
            }
        } catch (FileNotFoundException e) {
            LOG.error("Exception: JSONReader{ read() }", e);
        }
        return ticketList;
    }
}
