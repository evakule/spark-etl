package processor.functions;

import model.BalanceSheetEntity;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public final class BalanceSheetMapper implements MapFunction<Row, BalanceSheetEntity> {
  private static final long serialVersionUID = -2L;

  @Override
  public BalanceSheetEntity call(Row value) throws Exception {
    return BalanceSheetEntity.builder()
        .date(value.getAs("date"))
        .filingDate(value.getAs("filing_date"))
        .totalAssets(value.getAs("totalAssets"))
        .intangibleAssets(value.getAs("intangibleAssets"))
        .otherCurrentAssets(value.getAs("otherCurrentAssets"))
        .totalLiab(value.getAs("totalLiab"))
        .totalStockholderEquity(value.getAs("totalStockholderEquity"))
        .deferredLongTermLiab(value.getAs("deferredLongTermLiab"))
        .build();
  }
}
