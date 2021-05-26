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
        /*if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> paramValues = RequestParser.parseURI(exchange);
            OutputStream outputStream = exchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();
            ObjectMapper objectMapper = new ObjectMapper();
            //new ClientServiceImpl().addNewCardToAccount(paramValues.get("accountNumber"));
            // encode HTML content
            String htmlResponse = htmlBuilder.toString();
            // this line is a must
            exchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }*/
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
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
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            ObjectMapper mapper = new ObjectMapper();
            AccountNumberDto accountNumberDto = RequestParser.parseAccountNumberDtoFromPost(exchange);
            new ClientServiceImpl().addNewCardToAccount(accountNumberDto);
            //mapper.writeValue(out, new ClientServiceImpl().changeBalance(changeBalanceDto));
            //data = out.toByteArray();
            //return data;
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
