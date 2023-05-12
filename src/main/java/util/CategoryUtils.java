package util;

import functions.StockStructureMapper;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

public class CategoryUtils {

  public static Dataset<Row> selectDataByCategory(Dataset<Row> input, String dataCategory) {
    return input.selectExpr(dataCategory)
        .flatMap(new StockStructureMapper(), Encoders.bean(Row.class));
  }

}
