package loader.writer;

import org.apache.spark.sql.Dataset;

public class DatasetToCSVWriter<T> implements Writer {

  private final Dataset<T> data;
  private final String path;

  public DatasetToCSVWriter(Dataset<T> data, String path) {
    this.data = data;
    this.path = path;
  }

  @Override
  public void write() {
    data.coalesce(1).write().csv(path);
  }
}
