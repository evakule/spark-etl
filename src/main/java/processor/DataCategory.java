package processor;

public enum DataCategory {
  BALANCE_SHEET_Q("Financials.Balance_Sheet.quarterly.*");

  private final String category;

  DataCategory(String category) {
    this.category = category;
  }

  public String getCategoryPath() {
    return category;
  }
}
