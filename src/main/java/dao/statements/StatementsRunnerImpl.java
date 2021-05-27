package dao.statements;

import dao.DataSource;
import exception.UnexpectedServerException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementsRunnerImpl implements StatementsRunner {
    @Override
    public ResultSet runPreparedStatementSql(String query) {
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            return resultSet;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
    }

    @Override
    public void runStatementSql(String query) {
        try (Statement statement = DataSource.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
    }
}
