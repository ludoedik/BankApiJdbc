package entity;

import java.math.BigDecimal;

public class AccountEntity {
    private final int id;
    private final int clientId;
    private final BigDecimal currency;
    private final String accountNumber;

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public BigDecimal getCurrency() {
        return currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountEntity(int id, int clientId, BigDecimal currency, String accountNumber) {
        this.id = id;
        this.clientId = clientId;
        this.currency = currency;
        this.accountNumber = accountNumber;
    }
}
