package dao;


import dao.statements.StatementsRunner;
import dao.statements.StatementsRunnerImpl;
import dto.AccountNumberDto;
import dto.CardNumberDto;
import entity.CardEntity;
import exception.UnexpectedServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    /**
     * Object of StatementsRunner interface that provides runStatementSql and runPreparedStatementSql methods.
     * Those methods accept SQL query as input and return ResultSet object.
     * Appearance of this objects helps to properly test program inputs.
     */
    private final StatementsRunner statements;

    public CardDaoImpl(StatementsRunner statements) {
        this.statements = statements;
    }

    @Override
    public void addCard(AccountNumberDto accountNumber, String cardNumber) {
        String SQL_QUERY = "INSERT INTO CARD (ACCOUNT_ID, CARD_NUMBER, CARD_HOLDER ) " +
                "VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = " + accountNumber.getAccountNumber() + "), " + cardNumber + ", 'IVANOV STEPAN');";
        statements.runStatementSql(SQL_QUERY);

    }

    @Override
    public List<CardEntity> readCardListByAccountNumber(AccountNumberDto account) {
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT A on CARD.ACCOUNT_ID = A.ID WHERE ACCOUNT_NUMBER = '" +
                account.getAccountNumber() + "';";
        List<CardEntity> cardEntities = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("CARD.ID");
                String cardNumber = resultSet.getString("CARD_NUMBER");
                String cardHolder = resultSet.getString("CARD_HOLDER");
                int clientID = resultSet.getInt("CLIENT_ID");
                cardEntities.add(new CardEntity(id, cardNumber, account.getAccountNumber(), cardHolder, clientID));
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return cardEntities;
    }

    @Override
    public CardEntity getCard(CardNumberDto cardNumberDto) {
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT ON CARD.ACCOUNT_ID = ACCOUNT.ID WHERE CARD_NUMBER = " +
                cardNumberDto.getCardNumber() + ";";
        CardEntity cardEntity = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            int id = resultSet.getInt("CARD.ID");
            String cardNumber = resultSet.getString("CARD_NUMBER");
            String cardHolder = resultSet.getString("CARD_HOLDER");
            String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
            int clientID = resultSet.getInt("CLIENT_ID");
            cardEntity = new CardEntity(id, cardNumber, accountNumber, cardHolder, clientID);

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return cardEntity;
    }
}
