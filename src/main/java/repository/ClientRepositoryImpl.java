package repository;

import dao.ClientDaoImpl;
import dto.ClientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientRepositoryImpl implements ClientRepository {

    @Override
    public List<ClientDto> getClientList() throws SQLException{
        List<ClientDto> clientDtos = new ArrayList<>();
        new ClientDaoImpl().read().
                forEach(x -> clientDtos.add(new ClientDto(x.getName(), x.getFamilyName(), x.getSecondName())));
        return clientDtos;
    }


}
