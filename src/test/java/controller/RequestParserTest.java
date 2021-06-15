package controller;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    @Mock
    HttpExchange exchange;
    @Mock
    URI uri;
    @BeforeEach
    public void setUp () {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void parseURITest () {
        String testQuery = "http://localhost:8000/test?accountNumber=12422313241242414?balance=3412414";
        Mockito.when(exchange.getRequestURI()).thenReturn(uri);
        Mockito.when(uri.toString()).thenReturn(testQuery);
        Map<String, String> actual = RequestParser.parseURI(exchange);
        Map<String, String> expected = new HashMap<>();
        expected.put("accountNumber", "12422313241242414");
        expected.put("balance", "3412414");
        assertEquals(expected, actual);
    }


}