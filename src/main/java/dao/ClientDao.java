package dao;

import entity.ClientEntity;

import java.util.List;

public interface ClientDao {
    List<ClientEntity> read();
}
