package loader;

import loader.writer.DatasetToCSVWriter;
import loader.writer.DatasetToSQLTableWriter;
import loader.writer.Writer;
import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BalanceSheetLoader implements Loader<Dataset<BalanceSheetEntity>> {

  private static final String CSV_PATH = "output/balance_sheet";
  private static final String SQL_TABLE = "balance_sheet";
  private final Properties config;

  public BalanceSheetLoader(Properties config) {
    this.config = config;
  }

  @Override
  public void load(Dataset<BalanceSheetEntity> data) {
    List<Writer> writersList = new ArrayList<>();
    writersList.add(new DatasetToSQLTableWriter<>(data, config, SQL_TABLE));
    writersList.add(new DatasetToCSVWriter<>(data, CSV_PATH));
    writersList.stream().parallel().forEach(Writer::write);
  }
}
