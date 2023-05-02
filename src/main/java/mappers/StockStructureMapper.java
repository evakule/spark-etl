package mappers;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockStructureMapper implements FlatMapFunction<Row, Row> {
  private static final long serialVersionUID = -3L;

  @Override
  public Iterator<Row> call(Row row) throws Exception {
    List<Row> list = new ArrayList<>();
    for (String colName : row.schema().fieldNames()) {
      list.add(row.getAs(colName));
    }
    return list.iterator();
  }
}
