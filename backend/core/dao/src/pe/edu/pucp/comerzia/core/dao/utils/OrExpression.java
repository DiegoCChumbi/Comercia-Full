package pe.edu.pucp.comerzia.core.dao.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrExpression implements Expression {

  private final List<Expression> expressions;

  public OrExpression(List<Expression> expressions) {
    this.expressions = expressions;
  }

  public OrExpression(Expression... expressions) {
    this.expressions = Arrays.asList(expressions);
  }

  @Override
  public String toSql() {
    StringBuilder sb = new StringBuilder("(");
    for (int i = 0; i < expressions.size(); i++) {
      sb.append(expressions.get(i).toSql());
      if (i < expressions.size() - 1) {
        sb.append(" OR ");
      }
    }
    sb.append(")");
    return sb.toString();
  }

  @Override
  public List<Object> getParameters() {
    List<Object> params = new ArrayList<>();
    for (Expression expr : expressions) {
      params.addAll(expr.getParameters());
    }
    return params;
  }
}