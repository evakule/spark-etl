package job.factory;

import extractor.StocksExtractor;
import job.Job;
import job.SimpleJob;

public class BalanceSheetJobFactory {


  public Job getBalanceSheetJob() {
    return new SimpleJob<>(new StocksExtractor(), )
  }
}
