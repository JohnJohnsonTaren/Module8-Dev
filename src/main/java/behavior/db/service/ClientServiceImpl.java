package behavior.db.service;

import behavior.db.model.Client;

import java.sql.SQLException;
import java.util.List;

public abstract class ClientServiceImpl implements ClientService {
    private final ClientDaoService clientDao;

    public ClientServiceImpl(ClientDaoService clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public long create (String name) throws SQLException {
        Client newClient = new Client(name);
        Client savedClient = clientDao.save(newClient);
        return savedClient.getId();
    }

    @Override
    public String getById(long id) throws SQLException {
        Client client = clientDao.findById(id);
        if (client == null) {
            throw new IllegalArgumentException("Клієнта з таким " + id + " не знайдено");
        }
        return client.getName();
    }

    @Override
    public void setName (long id, String name) throws SQLException {
        Client clientToUpdate = clientDao.findById(id);
        if (clientToUpdate == null) {
            System.err.println("Клієнта з таким " + id + " не знайдено");
            return;
        }
        clientToUpdate.setName(name);
        clientDao.update(clientToUpdate);
    }

    @Override
    public void deleteById(long id) throws SQLException {
        boolean deleted = clientDao.delete(id);
        if (!deleted) {
            System.err.println("Клієнта з таким " + id + " не знайдено");
        }
    }

    @Override
    public List<Client> listAll() throws SQLException {
        return clientDao.findAll();
    }
}
