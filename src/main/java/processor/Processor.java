package processor;

public interface Processor<Input, Output> {

  Output transform(Input input);

}
