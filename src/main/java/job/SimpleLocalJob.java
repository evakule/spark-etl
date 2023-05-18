package job;

import extractor.Extractor;
import loader.Loader;
import processor.Processor;

public class SimpleLocalJob<K, V> implements Job {

  private final Extractor<K> extractor;
  private final Processor<K, V> processor;
  private final  Loader<V> loader;


  public SimpleLocalJob(Extractor<K> extractor,
                        Processor<K, V> processor,
                        Loader<V> loader) {
    this.extractor = extractor;
    this.processor = processor;
    this.loader = loader;
  }

  @Override
  public void launch() {
    K rawData = this.extractor.getSourceData();
    V processedData = processor.transform(rawData);
    loader.load(processedData);
  }
}
