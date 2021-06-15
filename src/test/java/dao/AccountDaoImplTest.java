package dao;

import dao.statements.StatementsRunner;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountDaoImplTest {
    AccountDaoImpl daoImpl;

    @Mock
    StatementsRunner statements;

    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;

    @Captor
    ArgumentCaptor<String> queryCaptor;

    @BeforeEach
    void setUp() {
        daoImpl = new AccountDaoImpl(statements);
    }

    @Test
    void testReadBalance() throws SQLException {
        String SQL_QUERY = "SELECT ID, CLIENT_ID, CURRENCY FROM ACCOUNT WHERE ACCOUNT_NUMBER = '12422313241242414';";
        final ResultSet resultSet = resultSetMock();
        try (MockedStatic<DataSource> utilities = Mockito.mockStatic(DataSource.class)) {
            utilities.when(DataSource::getConnection).thenReturn(connection);
            Mockito.when(connection.prepareStatement(queryCaptor.capture())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery())
                    .thenReturn(resultSet);

            final AccountNumberDto dto = new AccountNumberDto("12422313241242414");
            final AccountEntity accountEntity = daoImpl.readBalance(dto);
            assertEquals(accountEntity.getCurrency(), BigDecimal.valueOf(410));
            assertEquals(accountEntity.getClientId(), 1);
            assertEquals(accountEntity.getId(), 1);
            assertEquals(SQL_QUERY, queryCaptor.getValue());
        }
    }

    @Test
    void testChangeBalance() throws SQLException {
        final ChangeBalanceDto dto = new ChangeBalanceDto("12422313241242414", BigDecimal.valueOf(100));
        final AccountEntity entity = new AccountEntity(1, 1, BigDecimal.valueOf(410), dto.getAccountNumber());
        String SQL_QUERY = "UPDATE ACCOUNT SET CURRENCY = " + entity.getCurrency().add(dto.getAmount()) +
                " WHERE ID = " + entity.getId() + "';";
        Mockito.doNothing().when(statements).runStatementSql(queryCaptor.capture());
        statements.runStatementSql(SQL_QUERY);
        assertEquals (SQL_QUERY, queryCaptor.getValue());
    }

    private ResultSet resultSetMock() throws SQLException {
        final ResultSet testSet = Mockito.mock(ResultSet.class);
        Mockito.when(testSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(testSet.getInt("ID")).thenReturn(1);
        Mockito.when(testSet.getInt("CLIENT_ID")).thenReturn(1);
        Mockito.when(testSet.getBigDecimal("CURRENCY")).thenReturn(BigDecimal.valueOf(410));
        return testSet;
    }
}