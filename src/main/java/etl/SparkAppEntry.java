package etl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import static org.apache.spark.sql.functions.concat;

import static org.apache.spark.sql.types.DataTypes.IntegerType;
import static org.apache.spark.sql.types.DataTypes.StringType;

public class SparkAppEntry {

  private static final String CSV_DATA_PATH = "data/books.csv";
  private static final String PARQUET_DATA_PATH = "parquet";

  public static void main(String[] args) {
    SparkAppEntry app = new SparkAppEntry();
    app.start();
  }

  public void start() {
    SparkSession spark = getSession();
//    Dataset<etl.Book> csvData = readCsv(spark);
//    csvData.write().parquet(PARQUET_DATA_PATH);

    Dataset<Book> df = readParquet(spark);
    df.show(100);

//    Dataset<Row> castedDf = df.withColumn("Description", concat(lit("1-"), df.col("title")));
//    castedDf.show();
//
//    castedDf.write().mode("append").parquet(PARQUET_DATA_PATH);
//
//    Dataset<Row> newDf = readAsDf(spark);
//    newDf.show();



  }

  private Dataset<Book> readCsv(SparkSession spark) {
    StructType schema = new StructType()
        .add("id", IntegerType, true)
        .add("authorId", IntegerType, true)
        .add("title", StringType, true)
        .add("releaseDate", StringType, true)
        .add("link", StringType, true);

    return spark.read()
        .option("header", "true")
        .schema(schema)
        .csv(CSV_DATA_PATH).
            as(Encoders.bean(Book.class));
  }

  private Dataset<Row> readAsDf(SparkSession spark) {
    return spark.read().parquet(PARQUET_DATA_PATH);
  }

  private Dataset<Book> readParquet(SparkSession spark) {
    StructType schema = new StructType()
        .add("id", IntegerType, true)
        .add("authorId", IntegerType, true)
        .add("title", StringType, true)
        .add("releaseDate", StringType, true)
        .add("link", StringType, true);

    return spark.read()
        .option("header", "true")
        .schema(schema)
        .parquet(PARQUET_DATA_PATH).
            as(Encoders.bean(Book.class));
  }

  private SparkSession getSession() {
    return SparkSession.builder()
        .appName("parquet check")
        .config("spark.master", "local[*]")
        .getOrCreate();
  }
}



