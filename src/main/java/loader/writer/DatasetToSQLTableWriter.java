package loader.writer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SaveMode;

import java.util.Properties;

public class DatasetToSQLTableWriter<T> implements Writer {

  private final Dataset<T> data;
  private final Properties config;
  private final String sqlTable;

  public DatasetToSQLTableWriter(Dataset<T> data, Properties config, String sqlTable) {
    this.data = data;
    this.config = config;
    this.sqlTable = sqlTable;
  }

  @Override
  public void write() {
    data.write()
        .format("jdbc")
        .option("driver", config.getProperty("driver"))
        .option("url", config.getProperty("url"))
        .option("dbtable", sqlTable)
        .option("user", config.getProperty("user"))
        .option("password", config.getProperty("password"))
        .mode(SaveMode.Append)
        .save();
  }
}
