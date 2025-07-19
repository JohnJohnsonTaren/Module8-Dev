// Завдання №4 - створити клас для вибірки даних з БД
// Створи клас з назвою DatabaseQueryService.
// У цьому класі мають бути методи для кожного файлу з SELECT виразом з попереднього завдання.
// Кожний метод має:
//     - зчитувати відповідний .sql файл
//     - повертати потрібний результат
// Кожний метод називай згідно Java Code Conventions.
// Зверни увагу на коректний тип значення, що повертатиме метод.
// Наприклад, для файлу find_max_projects_client сигнатура методу
//      виглядатиме List<SQLResources.MaxProjectCountClient> findMaxProjectsClient().
// При цьому клас SQLResources.MaxProjectCountClient необхідно описати, наприклад:
//      public class SQLResources.MaxProjectCountClient {
//          private String name;
//          private int projectCount;
//      }
//
// Для роботи з БД використовуй написаний раніше тобою клас BehaviorDB.Database.
// Результат виконання завдання - методи для кожного SELECT запиту,
//      які можна викликати, наприклад, наступним чином:
//      List<SQLResources.MaxProjectCountClient> maxProjectCountClients =
//          new DatabaseQueryService().findMaxProjectsClient();
// Запусти так кожний метод, і переконайсь, що він повертає коректну інформацію
//      і під час запуску ніде не виникають Exceptions.

import behavior.db.Database;
import behavior.db.DatabaseMigrationService;
import behavior.db.model.LongestProject;
import behavior.db.model.MaxProjectCountClient;
import behavior.db.model.MaxSalaryWorker;
import behavior.db.model.YoungestEldestWorkers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<LongestProject> findLongestProject() throws Exception {
        List<LongestProject> result = new ArrayList<>();
        String sqlQuery = "";
        try {
            sqlQuery = Files.readString(Paths.get("sql/find_longest_project.sql"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nameClient = resultSet.getString("name");
                    result.add(new LongestProject(nameClient));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws Exception {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sqlQuery = "";
        try {
            sqlQuery = Files.readString(Paths.get("sql/find_max_projects_client.sql"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String clientName = resultSet.getString("name");
                    int projectCount = resultSet.getInt("project_count");
                    result.add(new MaxProjectCountClient(clientName, projectCount));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws Exception {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String sqlQuery = "";
        try {
            sqlQuery = Files.readString(Paths.get("sql/find_max_salary_worker.sql"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String clientName = resultSet.getString("name");
                    int salary = resultSet.getInt("salary");
                    result.add(new MaxSalaryWorker(clientName, salary));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() throws Exception {
        List<YoungestEldestWorkers> result = new ArrayList<>();
        String sqlQuery = "";
        try {
            sqlQuery = Files.readString(Paths.get("sql/find_youngest_eldest_workers.sql"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    String clientName = resultSet.getString("name");
                    Date birthday = resultSet.getDate("birthday");
                    result.add(new YoungestEldestWorkers(birthday, clientName));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        DatabaseMigrationService.main(args); // Ініціалізація структури БД
        DatabaseQueryService queryService = new DatabaseQueryService(); // Запуск DatabaseQueryService

        List<LongestProject> longestProject = queryService.findLongestProject();
        for (LongestProject client: longestProject) {
            System.out.println(client);
        }

        List<MaxProjectCountClient> maxProjectClients = queryService.findMaxProjectsClient();
        for (MaxProjectCountClient client: maxProjectClients) {
            System.out.println(client);
        }

        List<MaxSalaryWorker> maxSalaryWorker = queryService.findMaxSalaryWorker();
        for (MaxSalaryWorker client: maxSalaryWorker) {
            System.out.println(client);
        }

        List<YoungestEldestWorkers> youngestEldestWorkers = queryService.findYoungestEldestWorkers();
        for (YoungestEldestWorkers client: youngestEldestWorkers) {
            System.out.println(client);
        }

        Database.getInstance().close();
    }
}
