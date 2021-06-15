package dao.statements;

import java.sql.ResultSet;

public interface StatementsRunner {
    /**
     * Runs SQL query from parameters as prepared statement and returns result DataSet.
     * @param query
     * @return
     */
    ConnectionParams runPreparedStatementSql(String query);
    /**
     * Runs SQL query from parameters as prepared statement and returns nothing.
     * @param query
     */
    void runStatementSql(String query);
}
