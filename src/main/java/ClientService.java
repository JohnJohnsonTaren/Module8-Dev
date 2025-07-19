//Завдання №2 - напиши сервіс для CRUD операцій для сутності clients
//Напиши клас з назвою ClientService. Цей клас призначений для CRUD операцій з клієнтами.
// Мають бути наступні методи:
//      long create(String name) - додає нового клієнта з іменем name.
//              Повертає ідентифікатор щойно створеного клієнта.
//      String getById(long id) - повертає назву клієнта з ідентифікатором id
//      void setName(long id, String name) - встановлює нове ім'я name для клієнта з ідентифікатором id
//      void deleteById(long id) - видаляє клієнта з ідентифікатором id
//      List<Client> listAll() - повертає всіх клієнтів з БД у вигляді колекції об'єктів типу Client
//              (цей клас створи сам, у ньому має бути 2 поля - id та name)
//            Передбач валідацію вхідних даних в методах класу ClientService.
//            Якщо якісь вхідні дані некоректні (наприклад, ми намагаємось створити клієнта
//              з занадто коротким чи довгим іменем) - відповідний метод має викидати Exception.

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ClientService {
    private final Map<Long, Client> clients;
    private final AtomicLong nextId;

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 100;

    public ClientService() {
        this.clients = new ConcurrentHashMap<>();
        this.nextId = new AtomicLong(1);
    }

    public long create(String name) {
        validateName(name);
        long id = nextId.getAndIncrement();
        Client client = new Client(name, id);
        clients.put(id, client);
        return id;
    }

    public String getById (Long id) {
        Client client = clients.get(id);
        if (client == null) {
            throw new IllegalArgumentException("Клієнта з таким " + id + " не знайдено");
        }
        return client.getName();
    }

    public void setName (long id, String name) {
        validateName(name);
        Client client = clients.get(id);
        if (client == null) {
            throw new IllegalArgumentException("Клієнта з таким " + id + " не знайдено");
        }
        client.setName(name);
    }

    public void deliteById(long id) {
        Client removedClient = clients.remove(id);
        if (removedClient == null) {
            throw new IllegalArgumentException("Клієнта з таким " + id + " не знайдено");
        }
    }

    public List<Client> listAll() {
        return clients.values()
                .stream()
                .collect(Collectors.toList());
    }

    // Попитатся сократить!!!!
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Повинно бути заповненим");
        }
        String trimmedName = name.trim();
        if (trimmedName.length() < MIN_NAME_LENGTH || trimmedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Ім'я повинно бути не менше " + MIN_NAME_LENGTH + " та не більше " + MAX_NAME_LENGTH + " символів");
        }
    }
}
