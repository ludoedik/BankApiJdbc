package repository;

import dto.AccountNumberDto;
import dto.CardDto;
import dto.CardNumberDto;
import entity.CardEntity;

import java.util.List;

public interface CardRepository {
    void addCardToAccount(AccountNumberDto accountNumber, String cardNumber);
    List<CardDto> getCardList (AccountNumberDto account);
    CardDto getCardByNumber(CardNumberDto cardNumberDto);
}
