package dao;

import dto.AccountNumberDto;
import dto.CardNumberDto;
import entity.CardEntity;
import exception.AccountOperationsException;

import java.sql.SQLException;
import java.util.List;

public interface CardDao {
    CardEntity add(AccountNumberDto accountNumber, String cardNumber);
    List<CardEntity> readCardListByAccountNumber(AccountNumberDto accountNumber);
    CardEntity getCard(CardNumberDto cardNumberDto);
}
