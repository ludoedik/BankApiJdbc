package dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionParams {
    private final Connection connection;
    private final PreparedStatement preparedStatement;
    private final ResultSet resultSet;

    public ConnectionParams(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
    }


    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
