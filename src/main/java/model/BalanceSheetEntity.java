package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BalanceSheetEntity implements FundamentalEntity, Serializable {

  private String date;
  private String filingDate;
  private String totalAssets;
  private String intangibleAssets;
  private String otherCurrentAssets;
  private String totalLiab;
  private String totalStockholderEquity;
  private String deferredLongTermLiab;

}
