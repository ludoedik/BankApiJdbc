package dao;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.shortThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class AccountDaoImplTest {
    @Mock
    ResultSet testSet;

    @Test
    public void readBalanceTest() throws SQLException {
        assertNotNull(testSet);
        Mockito.when(testSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(testSet.getInt("ID")).thenReturn(1);
        Mockito.when(testSet.getInt("CLIENT_ID")).thenReturn(1);
        Mockito.when(testSet.getBigDecimal("CURRENCY")).thenReturn(BigDecimal.valueOf(410));
        //new AccountDaoImpl().readBalance();
    }

}