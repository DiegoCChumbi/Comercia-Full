package pe.edu.pucp.comerzia.db.utils;

import java.util.Collections;
import java.util.List;

public class SimpleExpression<T> implements Expression {

  private final Column<T> column;
  private final String operator;
  private final Object value;

  public SimpleExpression(Column<T> column, String operator, Object value) {
    this.column = column;
    this.operator = operator;
    this.value = value;
  }

  @Override
  public String toSql() {
    return column.getName() + " " + operator + " ?";
  }

  @Override
  public List<Object> getParameters() {
    return Collections.singletonList(value);
  }
}
