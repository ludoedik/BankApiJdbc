package dao;

import dao.statements.StatementsRunner;
import dto.AccountNumberDto;
import dto.CardNumberDto;
import entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardDaoImplTest {
    CardDaoImpl daoImpl;

    @Mock
    StatementsRunner statements;

    @Captor
    ArgumentCaptor<String> queryCaptor;

    @BeforeEach
    void setUp() {
        daoImpl = new CardDaoImpl(statements);
    }

    @Test
    void testReadCardListByAccountNumber() throws SQLException {
        final AccountNumberDto dto = new AccountNumberDto("12422313241242414");
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT A on CARD.ACCOUNT_ID = A.ID WHERE ACCOUNT_NUMBER = '" +
                dto.getAccountNumber() + "';";
        final ResultSet resultSet = resultSetMock();
        Mockito.when(statements.runPreparedStatementSql(queryCaptor.capture()))
                .thenReturn(resultSet);
        final List<CardEntity> entities = daoImpl.readCardListByAccountNumber(dto);
        assertEquals(6, entities.get(0).getId());
        assertEquals("1234567890123456", entities.get(0).getCardNumber());
        assertEquals("IVANOV STEPAN", entities.get(0).getCardHolder());
        assertEquals(1, entities.get(0).getClientId());
        assertEquals(7, entities.get(1).getId());
        assertEquals("1234567934629144", entities.get(1).getCardNumber());
        assertEquals("IVANOV STEPAN", entities.get(1).getCardHolder());
        assertEquals(1, entities.get(1).getClientId());
        assertEquals(SQL_QUERY, queryCaptor.getValue());
    }

    @Test
    void testAddCard() throws SQLException {
        final AccountNumberDto dto = new AccountNumberDto("12422313241242414");
        String cardNumber = "2234567890123456";
        String SQL_QUERY = "INSERT INTO CARD (ACCOUNT_ID, CARD_NUMBER, CARD_HOLDER ) " +
                "VALUES ((SELECT ID FROM ACCOUNT WHERE ACCOUNT_NUMBER = " + dto.getAccountNumber() + "), " + cardNumber + ", 'IVANOV STEPAN');";

        doNothing().when(statements).runStatementSql(queryCaptor.capture());
        statements.runStatementSql(SQL_QUERY);
        assertEquals(SQL_QUERY, queryCaptor.getValue());
    }

    @Test
    void testGetCard() throws SQLException{
        CardNumberDto dto = new CardNumberDto("1234567890123456");
        String SQL_QUERY = "SELECT * FROM CARD INNER JOIN ACCOUNT ON CARD.ACCOUNT_ID = ACCOUNT.ID WHERE CARD_NUMBER = " +
                dto.getCardNumber() + ";";
        ResultSet resultSet = resultSetMockGetCard();
        Mockito.when(statements.runPreparedStatementSql(queryCaptor.capture()))
                .thenReturn(resultSet);
        final CardEntity entity = daoImpl.getCard(dto);
        assertEquals(6, entity.getId());
        assertEquals("1234567890123456", entity.getCardNumber());
        assertEquals("IVANOV STEPAN", entity.getCardHolder());
        assertEquals(1, entity.getClientId());
        assertEquals(SQL_QUERY, queryCaptor.getValue());

    }

    private ResultSet resultSetMock() throws SQLException {
        final ResultSet testSet = Mockito.mock(ResultSet.class);
        Mockito.when(testSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(testSet.getInt("CARD.ID")).thenReturn(6).thenReturn(7);
        Mockito.when(testSet.getString("CARD_NUMBER")).thenReturn("1234567890123456").thenReturn("1234567934629144");
        Mockito.when(testSet.getString("CARD_HOLDER")).thenReturn("IVANOV STEPAN").thenReturn("IVANOV STEPAN");
        Mockito.when(testSet.getInt("CLIENT_ID")).thenReturn(1).thenReturn(1);
        return testSet;
    }

    private ResultSet resultSetMockGetCard() throws SQLException {
        final ResultSet testSet = Mockito.mock(ResultSet.class);
        Mockito.when(testSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(testSet.getInt("CARD.ID")).thenReturn(6);
        Mockito.when(testSet.getString("CARD_NUMBER")).thenReturn("1234567890123456");
        Mockito.when(testSet.getString("CARD_HOLDER")).thenReturn("IVANOV STEPAN");
        Mockito.when(testSet.getString("ACCOUNT_NUMBER")).thenReturn("IVANOV STEPAN");
        Mockito.when(testSet.getInt("CLIENT_ID")).thenReturn(1);
        return testSet;
    }


}