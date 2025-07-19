import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService();
    }

    @Test
    void testCreateNameOk() {
        long id = clientService.create("Client");
        assertTrue(id > 0);
        assertEquals("Client", clientService.getById(id));
    }

    @Test
    void testCreateNameNoNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.create(null);
        });
        assertEquals("Поле повинно бути заповненим", exception.getMessage());
    }

    @Test
    void testCreateNameEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.create("");
        });
        assertEquals("Поле повинно бути заповненим", exception.getMessage());
    }

    @Test
    void testCreateNameNoSpaces() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.create(" ");
        });
        assertEquals("Поле повинно бути заповненим", exception.getMessage());
    }

    @Test
    void testCreateNameMinValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           clientService.create("ЯН");
        });
        assertEquals("Ім'я повинно бути не менше 3 та не більше 100 символів", exception.getMessage());
    }

    @Test
    void testGetByIdOk() {
        long id = clientService.create("Є 1 клієнт");
        String name = clientService.getById(id);
        assertEquals("Є 1 клієнт", name);
    }

    @Test
    void testGetByIdNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.getById(123L);
        });
        assertEquals("Клієнта з таким 123 не знайдено", exception.getMessage());
    }

    @Test
    void testGetByIdNoCorrectly() {
        long id = clientService.create("Повинно бути заповненим");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.setName(id, null);
        });
    }

    @Test
    void testDeleteByIdOk() {
        long id = clientService.create("Клієнт для видалення");
        clientService.deliteById(id);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.getById(id);
        });
        assertEquals("Клієнта з таким " + id + " не знайдено", exception.getMessage());
        assertEquals(0, clientService.listAll().size());
    }

    @Test
    void testListAllClient() {
        clientService.create("Клієнт Я");
        clientService.create("Клієнт Н");
        List<Client> clients = clientService.listAll();
        assertEquals(2, clients.size());

        assertFalse(clients.stream().allMatch(c ->c.getName().equals("Клієнт Я")));
        assertFalse(clients.stream().allMatch(c ->c.getName().equals("Клієнт Н")));
    }
}