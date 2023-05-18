package loader;

import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;

public class BalanceSheetLoader implements Loader<Dataset<BalanceSheetEntity>> {

  private final static String PATH = "output/balance-sheet";

  public BalanceSheetLoader() {
  }

  @Override
  public void load(Dataset<BalanceSheetEntity> data) {
    data.coalesce(1).write().csv(PATH);
  }
}
