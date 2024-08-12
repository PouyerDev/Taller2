package taller2;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/numbers", new NumberHandler());
        ExecutorService executor = Executors.newFixedThreadPool(10000); // 8000 hilos
        server.setExecutor(executor);
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class NumberHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                StringBuilder response = new StringBuilder();
                for (int i = 0; i < 10; i++) { // Enviar 10 números
                    response.append("Number: ").append((int)(Math.random() * 100)).append("\n");
                }
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close(); // Cerrar la conexión
            } else {
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }
    }
}