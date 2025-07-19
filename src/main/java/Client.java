// Мають бути наступні методи:
//      long create(String name) - додає нового клієнта з іменем name.
//              Повертає ідентифікатор щойно створеного клієнта.
//      String getById(long id) - повертає назву клієнта з ідентифікатором id

public class Client {
    private String name;
    private long id;

    public Client(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
