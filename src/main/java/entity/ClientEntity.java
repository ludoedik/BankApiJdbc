package entity;

public class ClientEntity {
    private final int id;
    private final String name;
    private final String familyName;
    private final String secondName;

    public int getId() {
        return id;
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

    public ClientEntity(int id, String name, String familyName, String secondName) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}

