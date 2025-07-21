package behavior.db.model;
//Завдання №2 - напиши сервіс для CRUD операцій для сутності clients
//Напиши клас з назвою behavior.db.model.ClientService. Цей клас призначений для CRUD операцій з клієнтами.
// Мають бути наступні методи:
//      long create(String name) - додає нового клієнта з іменем name.
//              Повертає ідентифікатор щойно створеного клієнта.
//      String getById(long id) - повертає назву клієнта з ідентифікатором id
//      void setName(long id, String name) - встановлює нове ім'я name для клієнта з ідентифікатором id
//      void deleteById(long id) - видаляє клієнта з ідентифікатором id
//      List<behavior.db.model.Client> listAll() - повертає всіх клієнтів з БД у вигляді колекції об'єктів типу behavior.db.model.Client
//              (цей клас створи сам, у ньому має бути 2 поля - id та name)
//            Передбач валідацію вхідних даних в методах класу behavior.db.model.ClientService.
//            Якщо якісь вхідні дані некоректні (наприклад, ми намагаємось створити клієнта
//              з занадто коротким чи довгим іменем) - відповідний метод має викидати Exception.

import java.util.List;

public interface ClientService {
    long create(String name);

    String getById (Long id);

    String getById(long id);

    void setName (long id, String name);

    void deleteById(long id);

    List<Client> listAll();
}


