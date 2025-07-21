package behavior.db.service;
// Завдання №2 - створити клас для ініціалізації структури БД
// Створи клас з назвою BehaviorDB.DatabaseInitService.
// У цьому класі має бути метод public static void main(String[] args),
//      який зчитуватиме файл sql/init_db.sql і виконуватиме запити з цього класу у БД.
//
// Для роботи з БД використовуй написаний раніше тобою клас BehaviorDB.Database.
// Результат запуску цього класу - проініцалізована база даних
//      з коректно створеними таблицями та зв'язками між цими таблицями.

import org.flywaydb.core.Flyway;

public class DatabaseMigrationService {
    public static void main(String[] args) {
        String dbUrl = "jdbc:h2:./sql/test_db";

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway
                .configure()
                .dataSource(dbUrl, null, null)
                .load();

        // Start the migration
        flyway.migrate();
    }
}

