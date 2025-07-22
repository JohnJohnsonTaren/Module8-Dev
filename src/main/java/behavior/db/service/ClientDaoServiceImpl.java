package behavior.db.service;

import behavior.db.Database;
import behavior.db.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoServiceImpl implements ClientDaoService {
    public ClientDaoServiceImpl() {
    }

    private Connection getConnection() throws SQLException {
        return Database.getInstance().getConnection();
    }

    @Override
    public Client save(Client client) {
        if (client.getId() == 0) {
            String sql = "INSERT INTO client (name) VALUES (?)";

            Connection connection = null;
            try {
                connection = getConnection();
                try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, client.getName());
                    pstmt.executeUpdate();
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        client.setId(id);
                        return client;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Помилка при збереженні нового клієнта: " + e.getMessage());
                throw new RuntimeException("Не вдалося зберегти нового клієнта", e);
            }
            throw new RuntimeException("Не вдалося отримати ID для нового клієнта");
        } else {
            // Для існуючого клієнта, використовуємо update
            update(client);
            return client;
        }
    }

    @Override
    public Client findById(long id) {
        String sql = "SELECT id, name FROM client WHERE id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Client(rs.getString("name"), rs.getLong("id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Помилка при пошуку клієнта за ID: " + e.getMessage());
            throw new RuntimeException("Не вдалося знайти клієнта", e);
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE client SET name = ? WHERE id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, client.getName());
                pstmt.setLong(2, client.getId());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    System.err.println("Оновлення не відбулося: клієнта з ID " + client.getId() + " не знайдено.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Помилка при оновленні клієнта: " + e.getMessage());
            throw new RuntimeException("Не вдалося оновити клієнта", e);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM client WHERE id = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            System.err.println("Помилка при видаленні клієнта: " + e.getMessage());
            throw new RuntimeException("Не вдалося видалити клієнта", e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> client = new ArrayList<>();
        String sql = "SELECT id, name FROM client";
        Connection connection = null;
        try {
            connection = getConnection();
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    client.add(new Client(rs.getString("name"), rs.getLong("id")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Помилка при отриманні всіх клієнтів: " + e.getMessage());
            throw new RuntimeException("Не вдалося отримати список клієнтів", e);
        }
        return client;
    }
}
