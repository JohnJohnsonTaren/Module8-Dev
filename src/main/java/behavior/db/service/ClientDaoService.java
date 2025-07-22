package behavior.db.service;

import behavior.db.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDaoService {
    Client save(Client client) throws SQLException;

    Client findById(long id) throws SQLException;

    void update(Client client) throws SQLException;

    boolean delete(long id) throws SQLException;

    List <Client> findAll() throws SQLException;
}
