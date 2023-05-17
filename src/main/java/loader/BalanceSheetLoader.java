package loader;

import model.BalanceSheetEntity;
import org.apache.spark.sql.Dataset;

public class BalanceSheetLoader implements Loader<Dataset<BalanceSheetEntity>> {

  private final String path;

  public BalanceSheetLoader(String path) {
    this.path = path;
  }

  @Override
  public void loadLocallyToCsv(Dataset<BalanceSheetEntity> data) {
    data.coalesce(1).write().csv(path);
  }
}
