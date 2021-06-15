package dao;

import com.zaxxer.hikari.*;
import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource class that provides connection pool for database.
 * Uses HikariCP dependency.
 */
public class DataSource {
    private static HikariConfig config = new HikariConfig(
    );
    private static HikariDataSource ds;
    private static final String URL = "jdbc:h2:mem:ProjectTask";
    private static final String USER = "";
    private static final String PASSWORD = "";

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        /*
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );*/
        ds = new HikariDataSource(config);
    }


    public void LoadDatabase() throws SQLException, FileNotFoundException {
        /*Server tcpServer = Server.createTcpServer(
                "-tcpPort", "9091", "-tcpAllowOthers");
        tcpServer.start();*/
        Connection connection = getConnection();
        RunScript.execute(connection,
                new FileReader(getClass().getClassLoader()
                        .getResource("db/ProjectTaskDDL.sql").getFile()));
        RunScript.execute(connection,
                new FileReader(getClass().getClassLoader()
                        .getResource("db/ProjectTaskInsertDDL.sql").getFile()));
    }

    /**
     * Returns database connection from connection pool.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }



}
