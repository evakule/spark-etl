package app;

import extractor.Extractor;
import extractor.StocksExtractor;
import functions.RowPrinter;
import job.Job;
import job.SimpleLocalJob;
import loader.BalanceSheetLoader;
import loader.Loader;
import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import processor.BalanceSheetProcessor;
import processor.Processor;
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
    Processor<Dataset<Row>, Dataset<BalanceSheetEntity>> processor = new BalanceSheetProcessor();
    Loader<Dataset<BalanceSheetEntity>> loader = new BalanceSheetLoader("output/balance-sheet");

    Job job = new SimpleLocalJob<>(extractor, processor, loader);
    job.launch();
  }

  private void printer(Dataset<?> dataset) {
    dataset.printSchema();
    dataset.foreach(new RowPrinter<>());
  }
}
