package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import exception.AccountOperationsException;
import exception.UnexpectedServerException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class Handler {

    /**
     * Method handles request by type.
     * Accepts HttpExchange and RequestType as input.
     * @param exchange
     * @param requestType
     * @throws IOException
     */
    public void handleRequestType (HttpExchange exchange, RequestType requestType) throws IOException{
        if (requestType.equals(RequestType.GET)) {
            handleGet(exchange);
        }
        else if(requestType.equals(RequestType.POST)) {
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

    /**
     * Handles GET request.
     * Accepts HttpExchange as input, returns nothing.
     * @param exchange
     */
    public void handleGet(HttpExchange exchange) {
        try (OutputStream outputStream = exchange.getResponseBody()) {
            byte[] data = executeService(exchange);
            exchange.sendResponseHeaders(200, data.length);
            outputStream.write(data);
            outputStream.flush();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new UnexpectedServerException(500, "Inner server error.");
        }
    }

    /**
     * Handles POST request.
     * Accepts HttpExchange as input, returns nothing.
     * @param exchange
     * @throws IOException
     */
    public void handlePost(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        byte[] data = null;
        try {
            data = executeService(exchange);
        } catch (AccountOperationsException exception) {
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
        outputStream.flush();
        outputStream.close();
    }

    /**
     * Abstract method that defines which method to execute from Service layer.
     * Accepts HttpExchange as input and returns byte array.
     * @param exchange
     * @return
     */
    public abstract byte[] executeService(HttpExchange exchange);
}
