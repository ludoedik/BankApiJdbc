package dao.statements;

import java.sql.ResultSet;

public interface StatementsRunner {
    ResultSet runPreparedStatementSql(String query);
    void runStatementSql(String query);
}
