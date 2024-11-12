package pe.edu.pucp.comerzia.core.dao.utils;

public class Order {

  private final Column<?> column;
  private final String direction;

  public Order(Column<?> column, String direction) {
    this.column = column;
    this.direction = direction;
  }

  public String toSql() {
    return column.getName() + " " + direction;
  }
}
