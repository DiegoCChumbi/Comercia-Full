package pe.edu.pucp.comerzia.db.utils;

public class AggregateColumn<T> extends Column<T> {

  private final String function;
  private final Column<?> column;

  public AggregateColumn(String function, Column<?> column, Class<T> type) {
    super(function + "(" + column.getName() + ")", type, null, true);
    this.function = function;
    this.column = column;
  }

  @Override
  public String getName() {
    return function + "(" + column.getName() + ")";
  }
  // You can also override other methods if needed
}
