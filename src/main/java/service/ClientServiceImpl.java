package service;

import dto.*;
import exception.AccountOperationsException;
import repository.AccountRepositoryImpl;
import repository.CardRepositoryImpl;
import repository.ClientRepositoryImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientServiceImpl implements ClientService {
    @Override
    public List<ClientDto> getClientList(AccountNumberDto dto)  {
        List<ClientDto> clientDTOS = new ArrayList<>();
        try {
            return new ClientRepositoryImpl().getClientList();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return clientDTOS;
    }


    @Override
    public CardDto addNewCardToAccount(AccountNumberDto accountNumber){
        new CardRepositoryImpl().addCardToAccount(accountNumber, generateCardNumber());
        return null;
    }

    @Override
    public List<CardDto> getCardList(AccountNumberDto accountNumber) {
        return new CardRepositoryImpl().getCardList(accountNumber);
    }

    @Override
    public AccountDto getBalance(AccountNumberDto accountNumber) {
        return new AccountRepositoryImpl().getBalance(accountNumber);
    }

    @Override
    public void changeBalance(ChangeBalanceDto changeBalanceDto) {
        new AccountRepositoryImpl().changeBalance(changeBalanceDto);
    }

    /**
     * Util card number generating method.
     * @return
     */
    //TODO: fix this pls...
    private String generateCardNumber() {
        Random rnd = new Random();
        BigInteger integer = new BigInteger("1234567890123456");
        int generatedNumber = rnd.nextInt(100000000);
        /*BigInteger newCardNumber = integer.add(BigInteger.valueOf(generatedNumber));
        CardRepositoryImpl cardRepository = new CardRepositoryImpl();

        while (cardRepository.getCardByNumber(new CardNumberDto(newCardNumber.toString())).getId() == 0) {
            newCardNumber = integer.add(BigInteger.valueOf(rnd.nextInt(100000000)));
        }
        return newCardNumber.toString();*/
        return integer.add(BigInteger.valueOf(generatedNumber)).toString();
    }
}
