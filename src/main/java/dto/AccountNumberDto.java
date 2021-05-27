package dto;

public class AccountNumberDto {
    private final String accountNumber;

    public AccountNumberDto(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountNumberDto() {
        accountNumber = null;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
