package dao.statements;

import dao.DataSource;
import exception.UnexpectedServerException;

import java.sql.*;

public class StatementsRunnerImpl implements StatementsRunner {
    @Override
    public ConnectionParams runPreparedStatementSql(String query) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new ConnectionParams(connection, preparedStatement, resultSet);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
    }

    @Override
    public void runStatementSql(String query) {
        try (Connection connection = DataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
    }
}
