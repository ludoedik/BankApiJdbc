package repository;

import dao.AccountDaoImpl;
import dto.AccountDto;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;


public class AccountRepositoryImpl implements AccountRepository{
    @Override
    public AccountDto getBalance(AccountNumberDto accountNumber) {
        AccountEntity accountEntity = new AccountDaoImpl().readBalance(accountNumber);
        return new AccountDto(accountEntity.getId(), accountNumber.getAccountNumber(), accountEntity.getCurrency());
    }

    @Override
    public void changeBalance (ChangeBalanceDto changeBalanceDto) {
        new AccountDaoImpl().changeBalance(changeBalanceDto);
    }
}
