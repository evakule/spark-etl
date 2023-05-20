package app;

import functions.RowPrinter;
import job.Job;
import job.factory.JobFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
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

    SparkSession spark = SparkSession.builder()
        .appName(props.getProperty("name"))
        .master("local[*]")
        .getOrCreate();

    JobFactory jobFactory = new JobFactory(props, spark);

    jobFactory.getJobs().stream().parallel().forEach(Job::launch);

  }

  private void printer(Dataset<?> dataset) {
    dataset.printSchema();
    dataset.foreach(new RowPrinter<>());
  }
}
