package behavior.db.service;

import behavior.db.model.Client;

import java.util.List;

public interface ClientDaoService {
    Client save(Client client);

    Client findById(long id);

    void update(Client client);

    boolean delete(long id);

    List <Client> findAll();
}
