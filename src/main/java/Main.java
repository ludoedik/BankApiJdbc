import dao.DataSource;
import server.Server;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            DataSource ds = new DataSource();
            ds.LoadDatabase();
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
        Server server = new Server();
        server.startServer();
    }
}
