package dao;


import dto.AccountNumberDto;
import dto.CardNumberDto;
import entity.CardEntity;
import exception.UnexpectedServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public CardEntity add(AccountNumberDto accountNumber, String cardNumber) {
        String SQL_QUERY = "INSERT INTO CARD (ACCOUNT_ID, CARD_NUMBER, CARD_HOLDER ) " +
                "VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = " + accountNumber.getAccountNumber() + "), " + cardNumber + ", 'chel');";
        CardEntity cardEntity = new CardEntity(1, "", "", "", 1);
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(SQL_QUERY);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return cardEntity;
    }

    @Override
    public List<CardEntity> readCardListByAccountNumber(AccountNumberDto account) {
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT A on CARD.ACCOUNT_ID = A.ID WHERE ACCOUNT_NUMBER = " + account.getAccountNumber();
        List<CardEntity> cardEntities = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY);
             ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("CARD.ID");
                String cardNumber = rs.getString("CARD_NUMBER");
                String cardHolder = rs.getString("CARD_HOLDER");
                int clientID = rs.getInt("CLIENT_ID");
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
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT ON CARD.ACCOUNT_ID = ACCOUNT.ID WHERE CARD_NUMBER = " + cardNumberDto.getCardNumber() + ";";
        CardEntity cardEntity = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY);
             ResultSet rs = pst.executeQuery();) {
            rs.next();
            int id = rs.getInt("CARD.ID");
            String cardNumber = rs.getString("CARD_NUMBER");
            String cardHolder = rs.getString("CARD_HOLDER");
            String accountNumber = rs.getString("ACCOUNT_NUMBER");
            int clientID = rs.getInt("CLIENT_ID");
            cardEntity = new CardEntity(id, cardNumber, accountNumber, cardHolder, clientID);

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Server SQL error.");
        }
        return cardEntity;
    }
}
