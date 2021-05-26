package repository;

import dao.CardDao;
import dao.CardDaoImpl;
import dto.AccountNumberDto;
import dto.CardDto;
import dto.CardNumberDto;
import entity.CardEntity;

import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    @Override
    public CardEntity addCardToAccount(AccountNumberDto accountNumber, String cardNumber) {
        return new CardDaoImpl().add(accountNumber, cardNumber);
    }

    @Override
    public List<CardDto> getCardList(AccountNumberDto account){
        List<CardDto> cards = new ArrayList<>();
        new CardDaoImpl().readCardListByAccountNumber(account).forEach(x -> cards.add(
                new CardDto(x.getId(), x.getCardNumber(), x.getCardHolder())

        ));
        return cards;
    }

    @Override
    public CardDto getCardByNumber(CardNumberDto cardNumberDto) {
        CardEntity card = new CardDaoImpl().getCard(cardNumberDto);
        return new CardDto(card.getId(), card.getCardNumber(), card.getCardHolder());
    }
}
