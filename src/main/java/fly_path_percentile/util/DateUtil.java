package fly_path_percentile.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private static final String PATTERN = "dd.MM.yy H:mm";

    public static LocalDateTime dateParse(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(PATTERN));
    }
}
