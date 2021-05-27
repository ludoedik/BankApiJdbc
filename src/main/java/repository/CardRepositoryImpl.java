package repository;

import dao.CardDaoImpl;
import dao.statements.StatementsRunnerImpl;
import dto.AccountNumberDto;
import dto.CardDto;
import dto.CardNumberDto;
import entity.CardEntity;

import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    @Override
    public void addCardToAccount(AccountNumberDto accountNumber, String cardNumber) {
        new CardDaoImpl(new StatementsRunnerImpl()).addCard(accountNumber, cardNumber);
    }

    @Override
    public List<CardDto> getCardList(AccountNumberDto account){
        List<CardDto> cards = new ArrayList<>();
        new CardDaoImpl(new StatementsRunnerImpl()).readCardListByAccountNumber(account).forEach(x -> cards.add(
                new CardDto(x.getId(), x.getCardNumber(), x.getCardHolder())

        ));
        return cards;
    }

    @Override
    public CardDto getCardByNumber(CardNumberDto cardNumberDto) {
        CardEntity card = new CardDaoImpl(new StatementsRunnerImpl()).getCard(cardNumberDto);
        return new CardDto(card.getId(), card.getCardNumber(), card.getCardHolder());
    }
}
