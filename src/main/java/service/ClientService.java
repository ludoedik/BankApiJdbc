package service;

import dto.*;
import exception.AccountOperationsException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    /**
     * This method accepts account number and returns list of ClientDtos.
     * @param dto
     * @return
     */
    List<ClientDto> getClientList(AccountNumberDto dto);
    /**
     * Method creates CardRepositoryImpl object and calls addCardToAccount.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * @param accountNumber
     * @return
     */
    CardDto addNewCardToAccount (AccountNumberDto accountNumber);

    /**
     * Method creates CardRepositoryImpl object and calls getCardList.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * Returns List of CardDto objects.
     * @param accountNumber
     * @return
     */
    List<CardDto> getCardList (AccountNumberDto accountNumber);

    /**
     * Method creates AccountRepositoryImpl object and calls getBalance.
     * Accepts DTO with accountNumber in it and sends it to repository layer.
     * Returns AccountDto object.
     * @param accountNumber
     * @return
     */
    AccountDto getBalance(AccountNumberDto accountNumber);

    /**
     * Method creates AccountRepositoryImpl object and calls changeBalance method.
     * Accepts DTO with accountNumber, amount of money in it and sends it to repository layer.
     * Returns nothing but in future here could be some business logic.
     * @param changeBalanceDto
     */
    void changeBalance(ChangeBalanceDto changeBalanceDto);
}
