package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import dto.Dto;
import exception.BusinessException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public static Map<String, String> parseURI(HttpExchange httpExchange) {
        String[] splitNames = httpExchange.
                getRequestURI()
                .toString()
                .split("\\?");
        Map<String, String> URIValues = new HashMap<>();
        for (int i = 1; i < splitNames.length; i++) {
            String[] pair = splitNames[i].split("=");
            URIValues.put(pair[0], pair[1]);
        }
        URIValues.entrySet().forEach(System.out::println);
        return URIValues;
    }

    public static AccountNumberDto parseAccountNumberDtoFromPost(HttpExchange exchange){
        AccountNumberDto accountNumberDto = null;
        ObjectMapper mapper = new ObjectMapper();
        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        byte[] data = new byte[contentLength];
        try (InputStream is = exchange.getRequestBody()) {
            is.read(data);
            accountNumberDto = mapper.readValue(data, AccountNumberDto.class);
        }
        catch (IOException exception) {
            throw new BusinessException(400, "Can't parse POST request.");
        }
        return accountNumberDto;
    }

    /*public static  <T extends Dto> T parseDtoFromPost(HttpExchange exchange, Class<T> type) {
        T dto = null;
        ObjectMapper mapper = new ObjectMapper();
        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        byte[] data = new byte[contentLength];
        try (InputStream is = exchange.getRequestBody()) {
            is.read(data);
            dto = mapper.readValue(data, type);
        }
        catch (IOException exception) {
            throw new BusinessException(400, "Can't parse POST request.");
        }
        return dto;
    }*/

    public static ChangeBalanceDto parseChangeBalanceDtoFromPost(HttpExchange exchange) {
        ChangeBalanceDto changeBalanceDto = null;
        ObjectMapper mapper = new ObjectMapper();
        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        byte[] data = new byte[contentLength];
        try (InputStream is = exchange.getRequestBody()) {
            is.read(data);
            changeBalanceDto = mapper.readValue(data, ChangeBalanceDto.class);
        }
        catch (IOException exception) {
            throw new BusinessException(400, "Can't parse POST request.");
        }
        return changeBalanceDto;
    }

}
