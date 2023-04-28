package etl;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public class BookMapper implements MapFunction<Row, Book> {

  private static final long serialVersionUID = -2L;

  @Override
  public Book call(Row value) throws Exception {
    Book b = new Book();
    Integer maybe = 0;

    String some = value.getAs("id");
    if (some == null) {
      b.setId(maybe);
    } else {
      b.setId(Integer.parseInt(value.getAs("id")));
    }

    String aut = value.getAs("authorId");
    if (aut == null) {
      b.setAuthorId(maybe);
    } else {
      b.setAuthorId(Integer.parseInt(value.getAs("authorId")));
    }

    b.setTitle(value.getAs("title"));
    b.setReleaseDate(value.getAs("releaseDate"));
    b.setLink(value.getAs("link"));
    return b;
  }
}