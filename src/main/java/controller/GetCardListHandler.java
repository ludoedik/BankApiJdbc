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

public class GetCardListHandler extends Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleRequestType(exchange, RequestType.GET);
    }

    @Override
    public byte[] executeService(HttpExchange exchange) {
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            ObjectMapper mapper = new ObjectMapper();
            AccountNumberDto account = new AccountNumberDto(RequestParser.parseURI(exchange).get("accountNumber"));
            if (account.getAccountNumber() == null) {
                System.out.println("URI value is incorrect.");
                throw new BusinessException(400, "URI value is incorrect");
            }
            mapper.writeValue(out, new ClientServiceImpl().getCardList(account));
            data = out.toByteArray();
            return data;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Inner server problems");
        }
    }
}
