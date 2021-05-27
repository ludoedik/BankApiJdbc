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
    /**
     * Method accepts AccountNumberDto and cardNumber as parameters and returns nothing.
     * Calls addCard method from DAO layer.
     * @param accountNumber
     * @param cardNumber
     */
    @Override
    public void addCardToAccount(AccountNumberDto accountNumber, String cardNumber) {
        new CardDaoImpl(new StatementsRunnerImpl()).addCard(accountNumber, cardNumber);
    }

    /**
     * Method accepts AccountNumberDto as parameter and returns List of CardDto objects..
     * Calls readCardListByAccountNumber method from DAO layer, then converts entities into CardDto objects.
     * List of CardDto object contains information about all cards on requested account.
     * @param account
     * @return
     */
    @Override
    public List<CardDto> getCardList(AccountNumberDto account){
        List<CardDto> cards = new ArrayList<>();
        new CardDaoImpl(new StatementsRunnerImpl()).readCardListByAccountNumber(account).forEach(x -> cards.add(
                new CardDto(x.getId(), x.getCardNumber(), x.getCardHolder())

        ));
        return cards;
    }

    /**
     * Method accepts CardNumberDto objects and calls getCard method from DAO layer.
     * Converts entity object from DAO layer into CardDto object and returns it.
     * CardDto object contains information about card with requested card number.
     * @param cardNumberDto
     * @return
     */
    @Override
    public CardDto getCardByNumber(CardNumberDto cardNumberDto) {
        CardEntity card = new CardDaoImpl(new StatementsRunnerImpl()).getCard(cardNumberDto);
        return new CardDto(card.getId(), card.getCardNumber(), card.getCardHolder());
    }
}
