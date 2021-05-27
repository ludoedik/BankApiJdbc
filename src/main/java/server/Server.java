package server;

import controller.*;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    /**
     * Starts new httpserver on port 8000.
     * Creates API contexts.
     * @throws IOException
     */
    public void startServer() throws IOException {
        System.out.println("Initializing int socket address.");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        System.out.println("Creating contexts.");
        server.createContext("/test", new GetClientListHandler());
        server.createContext("/add", new AddNewCardToAccountHandler());
        server.createContext("/getList", new GetCardListHandler());
        server.createContext("/getBalance", new CheckBalanceHandler());
        server.createContext("/changeBalance", new ChangeBalanceHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Starting server");
        server.start();
        System.out.println("Server started on port 8000");
    }
}
