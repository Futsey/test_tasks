package fly_path_percentile.reader.service.date_convertor;

import java.time.LocalDateTime;

public interface DateConvertor<T> {

    T convertToLocalDateTime(String date, String time);
}
