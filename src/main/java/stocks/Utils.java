package stocks;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Utils {

  public static Dataset<Row> simpleLoad(SparkSession spark,
                                        String format,
                                        String dataPath) {
    return spark
        .read()
        .option("multiline","true")
        .format(format)
        .load(dataPath);
  }
}
