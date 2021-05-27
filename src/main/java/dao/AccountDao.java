package dao;

import dto.AccountDto;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;

import java.math.BigDecimal;

public interface AccountDao {

    /**
     * Reads Account information from DB, returns AccountEntity, accepts AccountNumberDto.
     *
     * @param accountNumber
     * @return
     */
    AccountEntity readBalance(AccountNumberDto accountNumber);
    /**
     * Changes balance in database on requested account. Accepts ChangeBalanceDto and returns nothing.
     * @param changeBalanceDto
     */
    void changeBalance(ChangeBalanceDto changeBalanceDto);
}
