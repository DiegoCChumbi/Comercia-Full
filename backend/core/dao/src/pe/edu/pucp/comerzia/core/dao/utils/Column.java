package pe.edu.pucp.comerzia.core.dao.utils;

public class Column<T> {

  private final String name;
  private final Class<T> type;
  private final String alias;
  private final boolean isAggregated;

  public static <T> Column<T> of(String name, Class<T> type) {
    return new Column<>(name, type);
  }

  public Column(String name, Class<T> type) {
    this(name, type, null, false);
  }

  protected Column(
    String name,
    Class<T> type,
    String alias,
    boolean isAggregated
  ) {
    this.name = name;
    this.type = type;
    this.alias = alias;
    this.isAggregated = isAggregated;
  }

  public String getName() {
    return name;
  }

  public Class<T> getType() {
    return type;
  }

  public String getAlias() {
    return alias;
  }

  public boolean isAggregated() {
    return isAggregated;
  }

  // Methods to create expressions
  public SimpleExpression<T> eq(T value) {
    return new SimpleExpression<>(this, "=", value);
  }

  public SimpleExpression<T> ne(T value) {
    return new SimpleExpression<>(this, "<>", value);
  }

  public SimpleExpression<T> gt(T value) {
    return new SimpleExpression<>(this, ">", value);
  }

  public SimpleExpression<T> lt(T value) {
    return new SimpleExpression<>(this, "<", value);
  }

  public SimpleExpression<T> ge(T value) {
    return new SimpleExpression<>(this, ">=", value);
  }

  public SimpleExpression<T> le(T value) {
    return new SimpleExpression<>(this, "<=", value);
  }

  public SimpleExpression<T> like(String pattern) {
    return new SimpleExpression<>(this, "LIKE", pattern);
  }

  // Aggregate functions
  public AggregateColumn<Double> avg() {
    return new AggregateColumn<>("AVG", this, Double.class);
  }

  public AggregateColumn<T> sum() {
    return new AggregateColumn<>("SUM", this, type);
  }

  public AggregateColumn<T> min() {
    return new AggregateColumn<>("MIN", this, type);
  }

  public AggregateColumn<T> max() {
    return new AggregateColumn<>("MAX", this, type);
  }

  public AggregateColumn<Long> count() {
    return new AggregateColumn<>("COUNT", this, Long.class);
  }

  // Ordering methods
  public Order asc() {
    return new Order(this, "ASC");
  }

  public Order desc() {
    return new Order(this, "DESC");
  }
}
