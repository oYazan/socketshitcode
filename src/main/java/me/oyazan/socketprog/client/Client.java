package me.oyazan.socketprog.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(reader.readLine());
            System.out.println(reader.readLine());

            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine();
                writer.println(userInput);
                //handle quit operation
                if (userInput.equalsIgnoreCase("quit")) {
                    break;
                }
                //read response from server
                String respone = reader.readLine();
                System.out.println(respone);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
