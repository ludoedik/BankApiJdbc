package repository;

import dao.AccountDaoImpl;
import dao.statements.StatementsRunnerImpl;
import dto.AccountDto;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;


public class AccountRepositoryImpl implements AccountRepository{
    /**
     * Converts AccountEntity object into AccountDto object and returns it.
     * AccountDto object contains current account balance information.
     * Method accepts AccountNumberDto as parameter.
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDto getBalance(AccountNumberDto accountNumber) {
        AccountEntity accountEntity = new AccountDaoImpl(new StatementsRunnerImpl()).readBalance(accountNumber);
        return new AccountDto(accountEntity.getId(), accountNumber.getAccountNumber(), accountEntity.getCurrency());
    }

    /**
     * Method accepts ChangeBalanceDto object and returns nothing.
     * Calls changeBalance method from DAO layer.
     * @param changeBalanceDto
     */
    @Override
    public void changeBalance (ChangeBalanceDto changeBalanceDto) {
        new AccountDaoImpl(new StatementsRunnerImpl()).changeBalance(changeBalanceDto);
    }
}
