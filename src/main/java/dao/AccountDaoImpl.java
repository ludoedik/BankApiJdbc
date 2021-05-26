package dao;

import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;
import exception.UnexpectedServerException;

import java.math.BigDecimal;
import java.sql.*;


public class AccountDaoImpl implements AccountDao {
    /**
     * Accepts AccountNumberDto. Reads Account information from DB, returns AccountEntity.
     * @param accountNumber
     * @return
     */
    @Override
    public AccountEntity readBalance(AccountNumberDto accountNumber) {
        String SQL_QUERY = "SELECT ID, CLIENT_ID, CURRENCY FROM ACCOUNT WHERE ACCOUNT_NUMBER = '" + accountNumber.getAccountNumber() + "';";
        AccountEntity accountEntity = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            int id = resultSet.getInt("ID");
            int client_id = resultSet.getInt("CLIENT_ID");
            BigDecimal currency = resultSet.getBigDecimal("CURRENCY");
            accountEntity = new AccountEntity(id, client_id, currency, accountNumber.getAccountNumber());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return accountEntity;
    }

    @Override
    public void changeBalance(ChangeBalanceDto changeBalanceDto) {
        AccountEntity entity = readBalance(new AccountNumberDto(changeBalanceDto.getAccountNumber()));
        String SQL_QUERY = "UPDATE ACCOUNT SET CURRENCY = " + entity.getCurrency().add(changeBalanceDto.getAmount()) + " WHERE ID = " + entity.getId() + ";";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(SQL_QUERY);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
    }
}