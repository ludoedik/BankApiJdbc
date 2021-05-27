package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.AccountNumberDto;
import exception.BusinessException;
import exception.UnexpectedServerException;
import service.ClientServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AddNewCardToAccountHandler extends Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            handlePost(exchange);
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
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            ObjectMapper mapper = new ObjectMapper();
            AccountNumberDto accountNumberDto = RequestParser.parseAccountNumberDtoFromPost(exchange);
            if (accountNumberDto.getAccountNumber() == null) {
                System.out.println("JSON body is incorrect.");
                throw new BusinessException(400, "JSON body is incorrect");
            }
            new ClientServiceImpl().addNewCardToAccount(accountNumberDto);
            return "ok".getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
            throw new BusinessException(400, "Can't parse JSON");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Inner server problems");
        }
    }
}
