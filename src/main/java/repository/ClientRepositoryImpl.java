package repository;

import dao.ClientDaoImpl;
import dto.ClientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientRepositoryImpl implements ClientRepository {
    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<ClientDto> getClientList() throws SQLException{
        List<ClientDto> clientDtos = new ArrayList<>();
        new ClientDaoImpl().readClientList().
                forEach(x -> clientDtos.add(new ClientDto(x.getName(), x.getFamilyName(), x.getSecondName())));
        return clientDtos;
    }


}
