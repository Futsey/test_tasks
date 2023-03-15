package fly_path_percentile.reader.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public final class DateUtil {

    private static final String PATTERN = "dd.MM.yy H:mm";

    public static List<TimeZone> showTimeZoneList() {
        var zones = new ArrayList<TimeZone>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        return zones;
    }

    public static LocalDateTime dateParse(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(PATTERN));
    }

    public static LocalDateTime getCityDateInTZ(String city, LocalDateTime date) {
        ZonedDateTime zonedCity = date.atZone(ZoneId.of(city));
        ZonedDateTime zonedCityInUTC = zonedCity.withZoneSameInstant(ZoneId.of(city));
        return zonedCityInUTC.toLocalDateTime();
    }

    public static LocalDateTime getCityDateInUTC(String city, LocalDateTime date) {
        ZonedDateTime zonedCity = date.atZone(ZoneId.of(city));
        ZonedDateTime zonedCityInUTC = zonedCity.withZoneSameInstant(ZoneId.of("UTC"));
        return zonedCityInUTC.toLocalDateTime();
    }
}
