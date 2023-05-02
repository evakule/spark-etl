package app;

import functions.RowPrinter;
import mappers.BalanceSheetMapper;
import mappers.StockStructureMapper;
import model.QuarterStock;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


import java.io.Serializable;


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

    Dataset<Row> stocks = spark
        .read()
        .option("multiline", "true")
        .format("json")
        .load("data/fundamental.json");

    Dataset<Row> financials = stocks.selectExpr("Financials.Balance_Sheet.yearly.*");

    Dataset<Row> result = financials.flatMap(new StockStructureMapper(), Encoders.bean(Row.class));
    Dataset<QuarterStock> stocks2 = result.map(new BalanceSheetMapper(), Encoders.bean(QuarterStock.class));

    stocks2.foreach(new RowPrinter());

    stocks2.printSchema();
    stocks2.show();
  }
}
