package functions;

import org.apache.spark.api.java.function.ForeachFunction;

public final class RowPrinter<T> implements ForeachFunction<T> {
  private static final long serialVersionUID = -3680381094052442862L;

  @Override
  public void call(T r) throws Exception {
    System.out.println(r);
  }
}