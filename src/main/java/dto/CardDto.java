package dto;

public class CardDto {
    private final int id;
    private final String cardNumber;
    private final String cardHolder;

    public CardDto(int id, String cardNumber, String cardHolder) {

        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public String getCardHolder() {
        return cardHolder;
    }
}
