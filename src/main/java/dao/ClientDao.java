package dao;

import entity.ClientEntity;

import java.util.List;

public interface ClientDao {
    /**
     * Reads list of all clients from database.
     * Returns List of ClientEntity objects.
     * @return
     */
    List<ClientEntity> readClientList();
}
