package app;

import extractor.Extractor;
import extractor.StocksExtractor;
import functions.RowPrinter;
import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import processor.BalanceSheetProcessor;
import util.ConfigProvider;


import java.io.Serializable;
import java.util.Properties;


public class StocksApplication implements Serializable {

  private final Properties props = new ConfigProvider("config/base-config.yaml").getProperties();

  public static void main(String[] args) {

    StocksApplication app = new StocksApplication();
    app.start();
  }

  private void start() {
    Extractor<Dataset<Row>> extractor = new StocksExtractor(props);

    Dataset<BalanceSheetEntity> balanceSheet = new BalanceSheetProcessor().transform(extractor.getSourceData());

    balanceSheet.foreach(new RowPrinter<>());

    balanceSheet.printSchema();
    balanceSheet.show();
  }
}
