package pe.edu.pucp.comerzia.core.dao.utils;

import java.util.Collections;
import java.util.List;

public class SimpleExpression<T> implements Expression {

  private final Column<T> column;
  private final String operator;
  private final boolean requiresValue;
  private final Object value;

  public SimpleExpression(
    Column<T> column,
    String operator,
    Object value,
    boolean requiresValue
  ) {
    this.column = column;
    this.operator = operator;
    this.value = value;
    this.requiresValue = requiresValue;
  }

  // Default to requiring a value
  public SimpleExpression(Column<T> column, String operator, Object value) {
    this(column, operator, value, true);
  }

  @Override
  public String toSql() {
    return column.getName() + " " + operator + (requiresValue ? " ?" : "");
  }

  @Override
  public List<Object> getParameters() {
    return requiresValue
      ? Collections.singletonList(value)
      : Collections.emptyList();
  }
}
