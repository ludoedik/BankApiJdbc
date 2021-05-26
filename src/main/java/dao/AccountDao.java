package dao;

import dto.AccountDto;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;

import java.math.BigDecimal;

public interface AccountDao {
    AccountEntity readBalance(AccountNumberDto accountNumber);
    void changeBalance(ChangeBalanceDto changeBalanceDto);
}
