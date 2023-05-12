package processor;

public interface Processor<K, V> {

  K transform(V input);

}
