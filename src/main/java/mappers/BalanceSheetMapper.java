package mappers;

import model.QuarterStock;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public final class BalanceSheetMapper implements MapFunction<Row, QuarterStock> {
  private static final long serialVersionUID = -2L;

  @Override
  public QuarterStock call(Row value) throws Exception {
    QuarterStock s = new QuarterStock();
    s.setDate(value.getAs("date"));
    s.setFilingDate(value.getAs("filing_date"));
    s.setCurrencySymbol(value.getAs("currency_symbol"));
    s.setTotalAssets(value.getAs("totalAssets"));
    return s;
  }
}