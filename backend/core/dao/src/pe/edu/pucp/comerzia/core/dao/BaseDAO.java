package pe.edu.pucp.comerzia.core.dao;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import pe.edu.pucp.comerzia.core.dao.utils.AndExpression;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.core.dao.utils.Expression;
import pe.edu.pucp.comerzia.core.dao.utils.Order;
import pe.edu.pucp.comerzia.core.db.DBManager;

/**
 * BaseDAO is a generic Data Access Object that provides basic CRUD operations,
 * query building, and transaction support for entities.
 *
 * @param <T> The type of the entity.
 * @param <K> The type of the primary key.
 */
public abstract class BaseDAO<T, K> {

  protected Class<T> entityClass;

  protected EntityMapper<T> entityMapper;

  /**
   * Constructor accepting the entity class type.
   *
   * @param entityClass The class of the entity.
   */
  protected BaseDAO(Class<T> entityClass, EntityMapper<T> entityMapper) {
    this.entityClass = entityClass;
    this.entityMapper = entityMapper;
  }

  /**
   * @return The name of the table associated with the entity.
   */
  protected abstract String getTableName();

  /**
   * @return The name of the primary key column.
   */
  protected abstract String getPrimaryKeyColumnName();

  /**
   * @return The EntityMapper responsible for mapping between ResultSet and entity.
   */
  protected EntityMapper<T> getEntityMapper() {
    return entityMapper;
  }

  /**
   * Finds an entity by its primary key.
   *
   * @param id The primary key.
   * @return An Optional containing the entity if found, or empty if not found.
   * @throws SQLException If a database access error occurs.
   */
  public Optional<T> findById(K id) throws SQLException {
    return query()
      .where(
        Column.of(getPrimaryKeyColumnName(), (Class<K>) id.getClass()).eq(
          (K) id
        )
      )
      .unique();
  }

  /**
   * Finds all entities in the table.
   *
   * @return A list of all entities.
   * @throws SQLException If a database access error occurs.
   */
  public List<T> findAll() throws SQLException {
    return query().list();
  }

  /**
   * Inserts a new entity into the table.
   *
   * @param entity The entity to insert.
   * @return The generated primary key.
   * @throws SQLException If a database access error occurs.
   */
  public K insert(T entity) throws SQLException {
    Map<String, Object> columns = mapEntityToColumns(entity);

    StringBuilder sql = new StringBuilder("INSERT INTO ")
      .append(getTableName())
      .append(" (");

    StringJoiner columnNames = new StringJoiner(", ");
    StringJoiner placeholders = new StringJoiner(", ");
    List<Object> params = new ArrayList<>();

    columns.forEach((column, value) -> {
      columnNames.add(column);
      placeholders.add("?");
      params.add(value);
    });

    sql
      .append(columnNames.toString())
      .append(") VALUES (")
      .append(placeholders.toString())
      .append(")");

    try (
      Connection conn = DBManager.getInstance().getConnection();
      PreparedStatement ps = conn.prepareStatement(
        sql.toString(),
        Statement.RETURN_GENERATED_KEYS
      )
    ) {
      setParameters(ps, params);
      System.out.println("---------------------- INSERT SQL: " + ps.toString());
      ps.executeUpdate();

      ResultSet generatedKeys = ps.getGeneratedKeys();
      if (generatedKeys.next()) {
        Object generatedId = generatedKeys.getObject(1);
        if (generatedId instanceof BigInteger) {
          generatedId = ((BigInteger) generatedId).intValue();
        }
        getEntityMapper().setGeneratedId(entity, generatedId);
        return (K) generatedId;
      } else {
        throw new SQLException("Failed to obtain generated ID.");
      }
    }
  }

  public Integer update(K id, T entity) throws SQLException {
    return update(id, entity, entityMapper.mapEntityToColumns(entity).keySet());
  }

  /**
   * Updates an entity in the table.
   * Performs a partial update based on the specified columns.
   *
   * @param entity       The entity to update.
   * @param columnsToUpdate A set of column names to update.
   * @throws SQLException If a database access error occurs.
   */
  public Integer update(K id, T entity, Set<Column<?>> columnsToUpdate)
    throws SQLException {
    Map<String, Object> columns = mapEntityToColumns(entity);

    // Filter columns to include only those specified
    Map<String, Object> columnsToUse = new LinkedHashMap<>();
    for (Column<?> column : columnsToUpdate) {
      String columnName = column.getName();
      if (
        columns.containsKey(columnName) &&
        !columnName.equals(getPrimaryKeyColumnName())
      ) {
        columnsToUse.put(columnName, columns.get(columnName));
      }
    }

    if (columnsToUse.isEmpty()) {
      throw new IllegalArgumentException("No columns specified for update.");
    }

    StringBuilder sql = new StringBuilder("UPDATE ")
      .append(getTableName())
      .append(" SET ");

    StringJoiner setClauses = new StringJoiner(", ");
    List<Object> params = new ArrayList<>();

    columnsToUse.forEach((column, value) -> {
      setClauses.add(column + " = ?");
      params.add(value);
    });

    sql
      .append(setClauses.toString())
      .append(" WHERE ")
      .append(getPrimaryKeyColumnName())
      .append(" = ?");

    params.add(id);

    try (
      Connection conn = DBManager.getInstance().getConnection();
      PreparedStatement ps = conn.prepareStatement(sql.toString())
    ) {
      setParameters(ps, params);
      return ps.executeUpdate();
    }
  }

  /**
   * Deletes an entity by its primary key.
   *
   * @param id The primary key of the entity to delete.
   * @throws SQLException If a database access error occurs.
   */
  public Integer delete(K id) throws SQLException {
    String sql =
      "DELETE FROM " +
      getTableName() +
      " WHERE " +
      getPrimaryKeyColumnName() +
      " = ?";
    List<Object> params = Collections.singletonList(id);

    try (
      Connection conn = DBManager.getInstance().getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)
    ) {
      setParameters(ps, params);
      return ps.executeUpdate();
    }
  }

  /**
   * Begins building a query.
   *
   * @return A QueryBuilder instance.
   */
  public QueryBuilder<T> query() {
    return new QueryBuilder<>(this);
  }

  /**
   * Executes a transaction with the specified callback.
   *
   * @param callback The transaction callback.
   * @throws SQLException If a database access error occurs.
   * @example
   * dao.executeTransaction(() -> {
   *  dao.insert(entity1);
   *  dao.insert(entity2);
   * });
   */
  public void executeTransaction(TransactionCallback callback)
    throws SQLException {
    try (Connection conn = DBManager.getInstance().getConnection()) {
      boolean originalAutoCommit = conn.getAutoCommit();
      conn.setAutoCommit(false);

      try {
        callback.execute();
        conn.commit();
      } catch (SQLException e) {
        conn.rollback();
        throw e;
      } finally {
        conn.setAutoCommit(originalAutoCommit);
      }
    }
  }

  /**
   * Sets parameters in a PreparedStatement based on their types.
   *
   * @param ps     The PreparedStatement.
   * @param params The list of parameters.
   * @throws SQLException If a database access error occurs.
   */
  protected void setParameters(PreparedStatement ps, List<Object> params)
    throws SQLException {
    for (int i = 0; i < params.size(); i++) {
      setParameter(ps, i + 1, params.get(i));
    }
  }

  /**
   * Sets a parameter in a PreparedStatement based on its type.
   *
   * @param ps    The PreparedStatement.
   * @param index The parameter index (starting from 1).
   * @param param The parameter value.
   * @throws SQLException If a database access error occurs.
   */
  protected void setParameter(PreparedStatement ps, int index, Object param)
    throws SQLException {
    if (param instanceof String) {
      ps.setString(index, (String) param);
    } else if (param instanceof Integer) {
      ps.setInt(index, (Integer) param);
    } else if (param instanceof Long) {
      ps.setLong(index, (Long) param);
    } else if (param instanceof Double) {
      ps.setDouble(index, (Double) param);
    } else if (param instanceof Float) {
      ps.setFloat(index, (Float) param);
    } else if (param instanceof Boolean) {
      ps.setBoolean(index, (Boolean) param);
    } else if (param instanceof java.sql.Date) {
      ps.setDate(index, (java.sql.Date) param);
    } else if (param instanceof java.util.Date) {
      ps.setTimestamp(index, new Timestamp(((java.util.Date) param).getTime()));
    } else if (param == null) {
      ps.setNull(index, Types.NULL);
    } else if (param.getClass().isEnum()) {
      ps.setString(index, param.toString());
    } else {
      throw new IllegalArgumentException(
        "Unsupported parameter type: " + param.getClass().getName()
      );
    }
  }

  /**
   * Retrieves the EntityMapper for the given discriminator values.
   */
  protected T mapResultSetToEntity(ResultSet rs) throws SQLException {
    // Retrieve discriminator values from the ResultSet
    Map<String, Object> discriminatorValues = new HashMap<>();
    for (String column : entityMapper.getDiscriminatorColumns().keySet()) {
      discriminatorValues.put(column, rs.getString(column));
    }

    // Determine the correct EntityMapper based on discriminator values
    // In this simplified example, we assume that entityMapper is capable of handling all cases.
    // In a real scenario, we might have a registry of mappers for different subclasses.
    if (!entityMapper.canMap(discriminatorValues)) {
      throw new SQLException(
        "No suitable mapper found for discriminator values: " +
        discriminatorValues
      );
    }

    return entityMapper.mapResultSetToEntity(rs);
  }

  protected Map<String, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = entityMapper.mapEntityToColumns(entity);
    Map<String, Object> transformedMap = new HashMap<>();
    for (Map.Entry<Column<?>, Object> entry : columns.entrySet()) {
      transformedMap.put(entry.getKey().getName(), entry.getValue());
    }

    transformedMap.putAll(entityMapper.getDiscriminatorColumns());

    return transformedMap;
  }

  /**
   * Callback interface for transaction execution.
   */

  public interface TransactionCallback {
    void execute() throws SQLException;
  }

  /**
   * Interface for mapping between ResultSet and entity objects.
   *
   * @param <T> The type of the entity.
   */
  public interface EntityMapper<T> {
    /**
     * Creates a new entity object.
     *
     * @return The new entity.
     */
    T createEntity();
    /**
     * Maps a ResultSet row to an entity object.
     *
     * @param rs The ResultSet.
     * @return The mapped entity.
     * @throws SQLException If a database access error occurs.
     */
    T mapResultSetToEntity(ResultSet rs) throws SQLException;

    /**
     * Maps an entity object to a map of column-value pairs.
     *
     * @param entity The entity to map.
     * @return A map of column names to values.
     */
    Map<Column<?>, Object> mapEntityToColumns(T entity);

    /**
     * Sets the generated ID into the entity.
     *
     * @param entity The entity to update.
     * @param id     The generated ID.
     */
    void setGeneratedId(T entity, Object id);

    /**
     * Retrieves the primary key from the entity.
     *
     * @param entity The entity.
     * @return The primary key.
     */
    Object getId(T entity);

    // New methods
    /**
     * Returns a map of discriminator columns and their expected values for this entity.
     */
    default Map<String, Object> getDiscriminatorColumns() {
      return Collections.emptyMap();
    }

    /**
     * Determines if this mapper can map the entity based on discriminator values.
     */
    default boolean canMap(Map<String, Object> discriminatorValues) {
      return true;
    }
  }

  /**
   * QueryBuilder class for building dynamic SQL queries.
   *
   * @param <T> The type of the entity.
   */
  public static class QueryBuilder<T> {

    private final BaseDAO<T, ?> dao;
    private final List<Object> parameters;
    private Expression whereExpression;
    private String orderByClause;
    private String groupByClause;
    private Expression havingExpression;
    private List<String> selectColumns;
    private Integer limit;
    private Integer offset;
    private List<String> joinClauses;
    private Class<?> resultType;

    public QueryBuilder(BaseDAO<T, ?> dao) {
      this.dao = dao;
      this.parameters = new ArrayList<>();
      this.selectColumns = new ArrayList<>();
      this.joinClauses = new ArrayList<>();
      this.resultType = dao.entityClass; // Default result type
    }

    public QueryBuilder<T> select(Column<?>... columns) {
      selectColumns.clear();
      for (Column<?> column : columns) {
        String colStr = column.getName();
        if (column.getAlias() != null) {
          colStr += " AS " + column.getAlias();
        }
        selectColumns.add(colStr);
      }
      return this;
    }

    public QueryBuilder<T> where(Expression expression) {
      if (whereExpression == null) {
        this.whereExpression = expression;
      } else {
        this.whereExpression = new AndExpression(whereExpression, expression);
      }
      return this;
    }

    public QueryBuilder<T> whereAll(Expression... expressions) {
      if (whereExpression == null) {
        this.whereExpression = new AndExpression(expressions);
      } else {
        this.whereExpression = new AndExpression(
          whereExpression,
          new AndExpression(expressions)
        );
      }
      return this;
    }

    public QueryBuilder<T> join(BaseDAO<?, ?> otherDao, JoinOn onClause) {
      return join("INNER JOIN", otherDao, onClause);
    }

    public QueryBuilder<T> leftJoin(BaseDAO<?, ?> otherDao, JoinOn onClause) {
      return join("LEFT JOIN", otherDao, onClause);
    }

    private QueryBuilder<T> join(
      String joinType,
      BaseDAO<?, ?> otherDao,
      JoinOn onClause
    ) {
      StringBuilder joinSql = new StringBuilder();
      joinSql
        .append(joinType)
        .append(" ")
        .append(otherDao.getTableName())
        .append(" ON ");
      Expression onExpression = onClause.build();
      joinSql.append(onExpression.toSql());
      parameters.addAll(onExpression.getParameters());
      joinClauses.add(joinSql.toString());
      return this;
    }

    public QueryBuilder<T> groupBy(Column<?>... columns) {
      StringBuilder sb = new StringBuilder();
      for (Column<?> column : columns) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(column.getName());
      }
      this.groupByClause = sb.toString();
      return this;
    }

    public QueryBuilder<T> having(Expression expression) {
      this.havingExpression = expression;
      return this;
    }

    public QueryBuilder<T> orderBy(Order... orders) {
      StringBuilder sb = new StringBuilder();
      for (Order order : orders) {
        if (sb.length() > 0) {
          sb.append(", ");
        }
        sb.append(order.toSql());
      }
      this.orderByClause = sb.toString();
      return this;
    }

    // Limit and Offset
    public QueryBuilder<T> limit(int limit) {
      this.limit = limit;
      return this;
    }

    public QueryBuilder<T> offset(int offset) {
      this.offset = offset;
      return this;
    }

    public <R> List<R> list(Class<R> resultClass) throws SQLException {
      this.resultType = resultClass;

      return executeQuery(resultClass);
    }

    public List<T> list() throws SQLException {
      return list(dao.entityClass);
    }

    /**
     * Executes a unique result query.
     *
     * @param resultClass The class of the result.
     */
    public <R> Optional<R> unique(Class<R> resultClass) throws SQLException {
      // Set limit to 2 to check if there is more than one result
      limit(2);

      List<R> results = executeQuery(resultClass);
      if (results.size() == 1) {
        return Optional.of(results.get(0));
      } else if (results.isEmpty()) {
        throw new SQLException("Expected one result, but found none");
      } else {
        throw new SQLException("Expected one result, but found multiple");
      }
    }

    public Optional<T> unique() throws SQLException {
      return unique(dao.entityClass);
    }

    /**
     * Executes a query and returns the first result.
     *
     * @param resultClass The class of the result.
     */
    public <R> Optional<R> first(Class<R> resultClass) throws SQLException {
      // Set limit to 1 to fetch only the first result
      limit(1);

      List<R> results = executeQuery(resultClass);
      if (results.isEmpty()) {
        return Optional.empty();
      } else {
        return Optional.of(results.get(0));
      }
    }

    public Optional<T> first() throws SQLException {
      return first(dao.entityClass);
    }

    // Count method
    public Integer count() throws SQLException {
      StringBuilder countSql = new StringBuilder(
        "SELECT COUNT(*) FROM "
      ).append(dao.getTableName());
      List<Object> finalParams = new ArrayList<>();

      for (String joinClause : joinClauses) {
        countSql.append(" ").append(joinClause);
      }

      if (whereExpression != null) {
        countSql.append(" WHERE ").append(whereExpression.toSql());
        finalParams.addAll(whereExpression.getParameters());
      }

      if (groupByClause != null) {
        countSql.append(" GROUP BY ").append(groupByClause);
      }

      if (havingExpression != null) {
        countSql.append(" HAVING ").append(havingExpression.toSql());
        finalParams.addAll(havingExpression.getParameters());
      }

      try (
        Connection conn = DBManager.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(countSql.toString())
      ) {
        dao.setParameters(ps, finalParams);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          return rs.getInt(1);
        } else {
          throw new SQLException("Failed to fetch count.");
        }
      }
    }

    private String buildSql() {
      StringBuilder sql = new StringBuilder();

      // SELECT clause
      if (selectColumns.isEmpty()) {
        sql.append("SELECT * ");
      } else {
        sql
          .append("SELECT ")
          .append(String.join(", ", selectColumns))
          .append(" ");
      }

      // FROM clause
      sql.append("FROM ").append(dao.getTableName());

      // JOIN clauses
      for (String joinClause : joinClauses) {
        sql.append(" ").append(joinClause);
      }

      // WHERE clause
      if (whereExpression != null) {
        sql.append(" WHERE ").append(whereExpression.toSql());
        parameters.addAll(whereExpression.getParameters());
      }

      // GROUP BY clause
      if (groupByClause != null) {
        sql.append(" GROUP BY ").append(groupByClause);
      }

      // HAVING clause
      if (havingExpression != null) {
        sql.append(" HAVING ").append(havingExpression.toSql());
        parameters.addAll(havingExpression.getParameters());
      }

      // ORDER BY clause
      if (orderByClause != null) {
        sql.append(" ORDER BY ").append(orderByClause);
      }

      // LIMIT and OFFSET clauses
      if (limit != null) {
        sql.append(" LIMIT ?");
        parameters.add(limit);
      }

      if (offset != null) {
        sql.append(" OFFSET ?");
        parameters.add(offset);
      }

      return sql.toString();
    }

    /**
     * Executes a query and returns a list of results.
     *
     * @param sql      The SQL query.
     * @param params   The list of parameters.
     * @param resultClass The class of the result.
     * @return A list of entities.
     * @throws SQLException If a database access error occurs.
     */
    private <R> List<R> executeQuery(Class<R> resultClass) throws SQLException {
      if (dao.entityMapper != null) {
        // Add discriminator columns to select columns
        Map<String, Object> discriminatorColumns =
          dao.entityMapper.getDiscriminatorColumns();
        if (!discriminatorColumns.isEmpty()) {
          AndExpression[] expressions =
            new AndExpression[discriminatorColumns.size()];

          int i = 0;
          for (Map.Entry<
            String,
            Object
          > entry : discriminatorColumns.entrySet()) {
            expressions[i++] = new AndExpression(
              Column.of(
                entry.getKey(),
                (Class<Object>) entry.getValue().getClass()
              ).eq(entry.getValue())
            );
          }

          whereAll(expressions);
        }
      }

      String sql = buildSql();
      List<Object> params = new ArrayList<>(parameters);

      try (
        Connection conn = DBManager.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)
      ) {
        dao.setParameters(ps, params);
        ResultSet rs = ps.executeQuery();

        List<R> results = new ArrayList<>();
        while (rs.next()) {
          if (resultClass.equals(Object[].class)) {
            ResultSetMetaData metaData = rs.getMetaData();
            Object[] row = new Object[metaData.getColumnCount()];
            for (int i = 0; i < row.length; i++) {
              row[i] = rs.getObject(i + 1);
            }
            results.add(resultClass.cast(row));
          } else if (
            Number.class.isAssignableFrom(resultClass) ||
            resultClass.equals(String.class)
          ) {
            results.add(rs.getObject(1, resultClass));
          } else if (resultClass.equals(Map.class)) {
            ResultSetMetaData metaData = rs.getMetaData();
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
              row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            results.add(resultClass.cast(row));
          } else {
            // Use a custom mapper if provided
            if (
              dao.getEntityMapper() != null &&
              resultClass.equals(dao.entityClass)
            ) {
              R entity = resultClass.cast(dao.mapResultSetToEntity(rs));
              results.add(entity);
            } else {
              throw new SQLException(
                "Unsupported result type: " + resultClass.getName()
              );
            }
          }
        }
        return results;
      }
    }

    // Functional interface for join ON clause
    @FunctionalInterface
    public interface JoinOn {
      Expression build();
    }
  }
}
