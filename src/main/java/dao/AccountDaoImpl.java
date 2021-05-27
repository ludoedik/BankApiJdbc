package dao;

import dao.statements.StatementsRunner;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;
import exception.UnexpectedServerException;

import java.math.BigDecimal;
import java.sql.*;


public class AccountDaoImpl implements AccountDao {

    StatementsRunner statements;

    public AccountDaoImpl(StatementsRunner statements) {
        this.statements = statements;
    }

    /**
     * Accepts AccountNumberDto. Reads Account information from DB, returns AccountEntity.
     * @param accountNumber
     * @return
     */
    @Override
    public AccountEntity readBalance(AccountNumberDto accountNumber) {
        String SQL_QUERY = "SELECT ID, CLIENT_ID, CURRENCY FROM ACCOUNT WHERE ACCOUNT_NUMBER = '" + accountNumber.getAccountNumber() + "';";
        AccountEntity accountEntity = null;

        try (ResultSet resultSet = statements.runPreparedStatementSql(SQL_QUERY)){
            resultSet.next();
            int id = resultSet.getInt("ID");
            int client_id = resultSet.getInt("CLIENT_ID");
            BigDecimal currency = resultSet.getBigDecimal("CURRENCY");
            accountEntity = new AccountEntity(id, client_id, currency, accountNumber.getAccountNumber());
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return accountEntity;
    }

    @Override
    public void changeBalance(ChangeBalanceDto changeBalanceDto) {
        AccountEntity entity = readBalance(new AccountNumberDto(changeBalanceDto.getAccountNumber()));
        String SQL_QUERY = "UPDATE ACCOUNT SET CURRENCY = " + entity.getCurrency().add(changeBalanceDto.getAmount()) + " WHERE ID = " + entity.getId() + ";";
        statements.runStatementSql(SQL_QUERY);
    }
}