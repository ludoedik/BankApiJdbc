package dto;

public class ClientDto extends Dto{
    private final String name;
    private final String familyName;
    private final String secondName;

    public ClientDto(String name, String familyName, String secondName) {
        this.name = name;
        this.familyName = familyName;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
