package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import dto.AccountNumberDto;
import dto.ChangeBalanceDto;
import exception.BusinessException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Util parser class.
 */
public class RequestParser {
    /**
     * Parses URI of request. Uses '?' and '=' as a delimiter.
     *
     * @param httpExchange
     * @return
     */
    public static Map<String, String> parseURI(HttpExchange httpExchange) {
        URI uri = httpExchange.getRequestURI();
        String query = uri.toString();
        String[] splitNames = query
                .split("\\?");
        Map<String, String> URIValues = new HashMap<>();
        for (int i = 1; i < splitNames.length; i++) {
            String[] pair = splitNames[i].split("=");
            URIValues.put(pair[0], pair[1]);
        }

        //URIValues.entrySet().forEach(System.out::println);
        return URIValues;
    }

    /**
     * Parses input JSON string from request into AccountNumberDto file.
     *
     * @param exchange
     * @return
     */
    public static AccountNumberDto parseAccountNumberDtoFromPost(HttpExchange exchange) {
        AccountNumberDto accountNumberDto = null;
        ObjectMapper mapper = new ObjectMapper();
        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        byte[] data = new byte[contentLength];
        try (InputStream is = exchange.getRequestBody()) {
            is.read(data);
            accountNumberDto = mapper.readValue(data, AccountNumberDto.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new BusinessException(400, "Can't parse POST request.");
        }
        return accountNumberDto;
    }

    /**
     * Parses input JSON string from request into ChangeBalanceDto file.
     *
     * @param exchange
     * @return
     */
    public static ChangeBalanceDto parseChangeBalanceDtoFromPost(HttpExchange exchange) {
        ChangeBalanceDto changeBalanceDto = null;
        ObjectMapper mapper = new ObjectMapper();
        Headers requestHeaders = exchange.getRequestHeaders();
        int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
        byte[] data = new byte[contentLength];
        try (InputStream is = exchange.getRequestBody()) {
            is.read(data);
            changeBalanceDto = mapper.readValue(data, ChangeBalanceDto.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new BusinessException(400, "Can't parse POST request.");
        }
        return changeBalanceDto;
    }

}
