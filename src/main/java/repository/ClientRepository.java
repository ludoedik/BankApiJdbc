package repository;

import dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {
    List<ClientDto> getClientList() throws SQLException;
}
