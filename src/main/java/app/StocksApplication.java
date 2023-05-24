package app;

import processor.functions.RowPrinter;
import job.Job;
import job.JobFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import util.YamlConfigProvider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class StocksApplication implements Serializable {

  private final Properties baseConfig = new YamlConfigProvider("config/base-config.yaml").getProperties();
  private final Properties postgresConfig = new YamlConfigProvider("config/postgre-sql-config.yaml").getProperties();

  public static void main(String[] args) {
    StocksApplication app = new StocksApplication();
    app.start();
  }

  private void start() {

    Map<String, Properties> configs = new HashMap<>();

    configs.put("base", baseConfig);
    configs.put("postgres", postgresConfig);

    SparkSession spark = SparkSession.builder()
        .appName(configs.get("base").getProperty("name"))
        .master("local[*]")
        .getOrCreate();

    JobFactory jobFactory = new JobFactory(configs, spark);

    jobFactory.getJobs().stream().parallel().forEach(Job::launch);

  }

  private void printer(Dataset<?> dataset) {
    dataset.printSchema();
    dataset.foreach(new RowPrinter<>());
  }
}
