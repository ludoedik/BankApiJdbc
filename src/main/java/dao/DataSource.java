package dao;

import com.zaxxer.hikari.*;

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

    static {
        config.setJdbcUrl("jdbc:h2:file:./data/ProjectTask");
        config.setUsername("");
        config.setPassword("");
        /*
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );*/
        ds = new HikariDataSource(config);
    }

    private DataSource() {
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
