package processor;

import functions.BalanceSheetMapper;
import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import processor.category.DataCategory;
import util.CategoryUtils;

public class BalanceSheetProcessor implements Processor<Dataset<Row>, Dataset<BalanceSheetEntity>> {

  @Override
  public Dataset<BalanceSheetEntity> transform(Dataset<Row> input) {

    Dataset<Row> balanceSheetQuarterly = CategoryUtils.selectDataByCategory(
        input, DataCategory.BALANCE_SHEET.getCategoryPath()
    );

    return balanceSheetQuarterly.map(new BalanceSheetMapper(), Encoders.bean(BalanceSheetEntity.class));
  }
}
