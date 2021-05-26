package entity;

public class CardEntity {
    private final int id;
    private final String cardNumber;
    private final String accountNumber;
    private final String cardHolder;
    private final int clientId;

    public CardEntity(int id, String cardNumber, String accountNumber, String cardHolder, int clientId) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cardHolder = cardHolder;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public int getClientId() {
        return clientId;
    }
}

