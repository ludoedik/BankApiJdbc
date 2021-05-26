package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.AccountNumberDto;
import exception.UnexpectedServerException;
import service.ClientServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class GetClientListHandler extends Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            handleGet(exchange);
        }
        else {
            OutputStream outputStream = exchange.getResponseBody();
            String response = "Method not allowed.";
            exchange.sendResponseHeaders(405, response.length());
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public byte[] executeService(HttpExchange exchange) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            AccountNumberDto account = new AccountNumberDto(RequestParser.parseURI(exchange).get("accountNumber"));
            mapper.writeValue(out, new ClientServiceImpl().getCardList(account));
            data = out.toByteArray();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Inner server error.");
        }
        return data;
    }
}
