package fly_path_percentile.reader.service.date_convertor;

import java.time.LocalDateTime;

public interface DateConverter {

    LocalDateTime parseToLocalDateTime(String date, String time);

    Long convertToLong(LocalDateTime date, String timeZone);
}
