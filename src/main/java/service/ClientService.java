package service;

import dto.*;
import exception.AccountOperationsException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    List<ClientDto> getClientList(AccountNumberDto dto);
    CardDto addNewCardToAccount (AccountNumberDto accountNumber);
    List<CardDto> getCardList (AccountNumberDto accountNumber);
    AccountDto getBalance(AccountNumberDto accountNumber);
    void changeBalance(ChangeBalanceDto changeBalanceDto);
}
