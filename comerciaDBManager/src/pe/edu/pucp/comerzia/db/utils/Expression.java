package pe.edu.pucp.comerzia.db.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Expression {
  String toSql();
  List<Object> getParameters();

  default Expression and(Expression... others) {
    List<Expression> expressions = new ArrayList<>();
    expressions.add(this);
    expressions.addAll(Arrays.asList(others));
    return new AndExpression(expressions);
  }

  default Expression or(Expression... others) {
    List<Expression> expressions = new ArrayList<>();
    expressions.add(this);
    expressions.addAll(Arrays.asList(others));
    return new OrExpression(expressions);
  }
}
