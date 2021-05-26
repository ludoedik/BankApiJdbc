package dao;

import entity.ClientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    public ClientDaoImpl() {
    }

    public List<ClientEntity> read()  {

        String SQL_QUERY = "select * from CLIENT";
        List<ClientEntity> clientEntities = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery();) {
            clientEntities = new ArrayList<>();
            ClientEntity client;
            while ( rs.next() ) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String familyName = rs.getString("family_name");
                String secondName = rs.getString("second_name");
                client = new ClientEntity(id, name, familyName, secondName);
                clientEntities.add( client );
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return clientEntities;
    }
}
