package dao;

import dto.AccountNumberDto;
import dto.CardNumberDto;
import entity.CardEntity;
import exception.AccountOperationsException;

import java.sql.SQLException;
import java.util.List;

public interface CardDao {
    /**
     * Adds card with set card number and account number to database.
     * @param accountNumber
     * @param cardNumber
     */
    void addCard(AccountNumberDto accountNumber, String cardNumber);

    /**
     * Gets list of cards by requested account.
     * Accepts AccountNumberDto as input and returns List of CardEntity objects.
     * @param accountNumber
     * @return
     */
    List<CardEntity> readCardListByAccountNumber(AccountNumberDto accountNumber);

    /**
     * Gets card with requested card number from database.
     * Accepts CardNumberDto as input and returns CardEntity object.
     * @param cardNumberDto
     * @return
     */
    CardEntity getCard(CardNumberDto cardNumberDto);
}
