package behavior.db.service;
//Завдання №2 - напиши сервіс для CRUD операцій для сутності clients
//Напиши клас з назвою behavior.db.service.ClientService. Цей клас призначений для CRUD операцій з клієнтами.
// Мають бути наступні методи:
//      long create(String name) - додає нового клієнта з іменем name.
//              Повертає ідентифікатор щойно створеного клієнта.
//      String getById(long id) - повертає назву клієнта з ідентифікатором id
//      void setName(long id, String name) - встановлює нове ім'я name для клієнта з ідентифікатором id
//      void deleteById(long id) - видаляє клієнта з ідентифікатором id
//      List<behavior.db.model.Client> listAll() - повертає всіх клієнтів з БД у вигляді колекції об'єктів типу behavior.db.model.Client
//              (цей клас створи сам, у ньому має бути 2 поля - id та name)
//            Передбач валідацію вхідних даних в методах класу behavior.db.service.ClientService.
//            Якщо якісь вхідні дані некоректні (наприклад, ми намагаємось створити клієнта
//              з занадто коротким чи довгим іменем) - відповідний метод має викидати Exception.

import behavior.db.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    long create(String name) throws SQLException;

    String getById (Long id);

    String getById(long id) throws SQLException;

    void setName (long id, String name) throws SQLException;

    void deleteById(long id) throws SQLException;

    List<Client> listAll() throws SQLException;
}


