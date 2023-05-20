package job.factory;

import extractor.StocksExtractor;
import job.Job;
import job.SimpleJob;
import loader.BalanceSheetLoader;
import org.apache.spark.sql.SparkSession;
import processor.BalanceSheetProcessor;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class JobFactory {

  private final Properties properties;
  private final SparkSession spark;
  private final Set<Job> jobs = new HashSet<>();

  public JobFactory(Properties properties, SparkSession spark) {
    this.properties = properties;
    this.spark = spark;

    jobs.add(this.getBalanceSheetJob());

  }

  public Set<Job> getJobs() {
    return jobs;
  }


  public Job getBalanceSheetJob() {
    return new SimpleJob<>(
        new StocksExtractor(properties, spark),
        new BalanceSheetProcessor(),
        new BalanceSheetLoader()
    );
  }
}
