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
    /**
     * This method accepts account number and calls a method of client repository
     * @param dto
     * @return
     */
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

    /**
     * Method creates CardRepositoryImpl object and calls addCardToAccount.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * Returns nothing but lately here could be some business logic.
     * @param accountNumber
     * @return
     */
    @Override
    public CardDto addNewCardToAccount(AccountNumberDto accountNumber){
        new CardRepositoryImpl().addCardToAccount(accountNumber, generateCardNumber());
        return null;
    }

    /**
     * Method creates CardRepositoryImpl object and calls getCardList.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * Returns List of CardDto objects.
     * @param accountNumber
     * @return
     */
    @Override
    public List<CardDto> getCardList(AccountNumberDto accountNumber) {
        return new CardRepositoryImpl().getCardList(accountNumber);
    }

    /**
     * Method creates AccountRepositoryImpl object and calls getBalance.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * Returns AccountDto
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDto getBalance(AccountNumberDto accountNumber) {
        return new AccountRepositoryImpl().getBalance(accountNumber);
    }

    /**
     * Method creates AccountRepositoryImpl object and calls changeBalance method.
     * Accepts DTO with accountNumber, amount of money in it and sends it to repository layer.
     * Returns nothing but lately here could be some business logic.
     * @param changeBalanceDto
     */
    @Override
    public void changeBalance(ChangeBalanceDto changeBalanceDto) {
        new AccountRepositoryImpl().changeBalance(changeBalanceDto);
    }

    /**
     * Util function.
     * @return
     */
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
