package fly_path_percentile.reader.service.date_convertor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static fly_path_percentile.reader.util.DateUtil.*;

public class StringToLocalDateTimeConverter implements DateConverter {

    @Override
    public LocalDateTime parseToLocalDateTime(String date, String time) {
        StringBuilder sb = new StringBuilder(date);
        sb.append(" ");
        sb.append(time);
        return dateParse(String.valueOf(sb));
    }

    @Override
    public Long convertToLong(LocalDateTime date, String timeZone) {
        ZonedDateTime zdt = date.atZone(ZoneId.of(timeZone));
        return zdt.toInstant().toEpochMilli();
    }
}
