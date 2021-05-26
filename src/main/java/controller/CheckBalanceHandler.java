package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.AccountNumberDto;
import exception.AccountOperationsException;
import exception.BusinessException;
import exception.UnexpectedServerException;
import service.ClientServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CheckBalanceHandler extends Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        /*OutputStream outputStream = exchange.getResponseBody();
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] data = null;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
                AccountNumberDto account = RequestParser.parseAccountNumberDtoFromPost(exchange);
                objectMapper.writeValue(out, new ClientServiceImpl().getBalance(account));
                data = out.toByteArray();
            } catch (JsonProcessingException exception) {
                System.out.println(exception.getMessage());
                String response = exception.getMessage();
                exchange.sendResponseHeaders(500, response.length());
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }
            catch (AccountOperationsException exception) {
                String response = exception.getMessage();
                System.out.println(response);
                exchange.sendResponseHeaders(exception.getErrorCode(), response.length());
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }
            if (data == null) {
                String response = "Not found";
                exchange.sendResponseHeaders(404, 0);
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            } else {
                exchange.sendResponseHeaders(200, data.length);
                outputStream.write(data);
            }
        }
        else {
            String response = "Method not allowed.";
            exchange.sendResponseHeaders(405, response.length());
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        }
        outputStream.flush();
        outputStream.close();*/
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
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            ObjectMapper mapper = new ObjectMapper();
            AccountNumberDto account = new AccountNumberDto(RequestParser.parseURI(exchange).get("accountNumber"));
            mapper.writeValue(out, new ClientServiceImpl().getBalance(account));
            data = out.toByteArray();
            return data;
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
            throw new BusinessException(400, "Can't parse JSON");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Inner server problems");
        }
    }
}
