package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QuarterStock implements Serializable {

  private String date;
  private String filingDate;
  private String currencySymbol;
  private String totalAssets;

}
