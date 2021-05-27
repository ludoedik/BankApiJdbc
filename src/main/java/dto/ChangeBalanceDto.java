package dto;

import java.math.BigDecimal;

public class ChangeBalanceDto {
    private final String accountNumber;
    private final BigDecimal amount;

    public ChangeBalanceDto(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public ChangeBalanceDto(){
        accountNumber = null;
        amount = null;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
