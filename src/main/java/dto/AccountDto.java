package dto;

import java.math.BigDecimal;

public class AccountDto extends Dto{
    private final int id;
    private final String accountNumber;
    private final BigDecimal balance;

    public AccountDto(int id, String accountNumber, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
