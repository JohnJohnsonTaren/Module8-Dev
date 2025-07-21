package behavior.db.model;

import java.util.List;

public abstract class ClientServiceImpl implements ClientService {
    private final ClientDaoService clientDao;

    public ClientServiceImpl(ClientDaoService clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public long create (String name) {
        Client newClient = new Client(name);
        Client savedClient = clientDao.save(newClient);
        return savedClient.getId();
    }

    @Override
    public String getById(long id) {
        Client client = clientDao.findById(id);
        if (client == null) {
            throw new IllegalArgumentException("Клієнта з таким " + id + " не знайдено");
        }
        return client.getName();
    }

    @Override
    public void setName (long id, String name) {
        Client clientToUpdate = clientDao.findById(id);
        if (clientToUpdate == null) {
            System.err.println("Клієнта з таким " + id + " не знайдено");
            return;
        }
        clientToUpdate.setName(name);
        clientDao.update(clientToUpdate);
    }

    @Override
    public void deleteById(long id) {
        boolean deleted = clientDao.delete(id);
        if (!deleted) {
            System.err.println("Клієнта з таким " + id + " не знайдено");
        }
    }

    @Override
    public List<Client> listAll() {
        return clientDao.findAll();
    }

//private void validateName(String name) {
//    if (name == null || name.trim().isEmpty()) {
//        throw new IllegalArgumentException("Повинно бути заповненим");
//    }
//    String trimmedName = name.trim();
//    if (trimmedName.length() < MIN_NAME_LENGTH || trimmedName.length() > MAX_NAME_LENGTH) {
//        throw new IllegalArgumentException("Ім'я повинно бути не менше " + MIN_NAME_LENGTH + " та не більше " + MAX_NAME_LENGTH + " символів");
//    }
//}


}
