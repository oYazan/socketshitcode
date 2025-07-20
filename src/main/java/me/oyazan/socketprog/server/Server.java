package me.oyazan.socketprog.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Listening on localhost:" + PORT);
        //accept connection from client
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket client = server.accept();
                System.out.println("Connected: " + client.getRemoteSocketAddress());
                handleClient(client);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleClient(Socket client) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {

            writer.println("Connected at localhost:" + PORT);
            writer.println("Enter expressions like '2 + 7' or \"quit\" to stop:");
            String line;
            while ((line = reader.readLine()) != null) {
                //handle quit input to stop the loop
                if (line.equalsIgnoreCase("quit")) {
                    writer.println("cya! meow");
                    break;
                }
                //handle the expression with ErrorOperationHandler class and output the result to the client
                String result = ErrorOperationHandler.evaluate(line);
                writer.println("Output: " + result);
            }

        } catch (IOException e) {
            System.out.println("Error, client disconnected: " + e.getMessage());
        } finally {
            try {
                //make sure the socket is closed
                client.close();
            } catch (IOException e) {}
        }
    }
}
