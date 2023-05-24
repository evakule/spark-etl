package job.factory;

import extractor.StocksExtractor;
import job.Job;
import job.SimpleJob;
import loader.BalanceSheetLoader;
import org.apache.spark.sql.SparkSession;
import processor.BalanceSheetProcessor;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class JobFactory {

  private final Map<String, Properties> config;
  private final SparkSession spark;
  private final Set<Job> jobs = new HashSet<>();


  public JobFactory(Map<String, Properties> config, SparkSession spark) {
    this.config = config;
    this.spark = spark;

    jobs.add(this.getBalanceSheetJob());

  }

  public Set<Job> getJobs() {
    return jobs;
  }

  public Job getBalanceSheetJob() {
    return new SimpleJob<>(
        new StocksExtractor(config.get("base"), spark),
        new BalanceSheetProcessor(),
        new BalanceSheetLoader(config.get("postgres"))
    );
  }
}
