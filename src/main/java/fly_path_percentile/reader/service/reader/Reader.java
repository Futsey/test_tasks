package fly_path_percentile.reader.service.reader;

import java.util.List;

public interface Reader<T> {

    List<T> read(String path);
}
