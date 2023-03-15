package fly_path_percentile.service.reader;

import java.util.List;

public interface Reader<T> {

    List<T> read(String path);
}
