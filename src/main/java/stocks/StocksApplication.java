package stocks;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StocksApplication implements Serializable {
  public static void main(String[] args) {

    StocksApplication app = new StocksApplication();
    app.start();
  }

  private void start() {
    SparkSession spark = SparkSession.builder()
        .appName("Stocks Analyzer")
        .master("local[*]")
        .getOrCreate();

    Dataset<Row> stocks = Utils.simpleLoad(spark, "json", "data/some.json");

//    Dataset<Row> financials = stocks.selectExpr("Financials.Balance_Sheet.yearly as yearly");
//
//    Dataset<Row> years = financials.selectExpr("yearly.*");

    Dataset<Row> yearly = stocks.selectExpr("Financials.Balance_Sheet.yearly.*");

    Dataset<Row> result = yearly.flatMap(
        (FlatMapFunction<Row, Row>) row -> {
          List<Row> list = new ArrayList<>();
          for (String colName : row.schema().fieldNames()) {
            list.add(row.getAs(colName));
          }
          return list.iterator();
        }, Encoders.bean(Row.class)
    );

    result.foreach(new RowPrinter());
    result.printSchema();

  }

  private final class RowPrinter implements ForeachFunction<Row> {
    private static final long serialVersionUID = -3680381094052442862L;

    @Override
    public void call(Row r) throws Exception {
      System.out.println(r);
    }
  }
}
