package repository;

import dto.AccountDto;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;


public interface AccountRepository {
    AccountDto getBalance(AccountNumberDto accountNumber);

    void changeBalance(ChangeBalanceDto changeBalanceDto);
}
