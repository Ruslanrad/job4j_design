package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = "";
                    switch (input.readLine()) {
                        case "GET /?msg=Exit HTTP/1.1":
                            server.close();
                            break;
                        case "GET /?msg=Hello HTTP/1.1":
                            message = "Hello";
                            break;
                        default: message = "What";
                        break;
                    }
                    output.write(message.getBytes());
                    output.flush();
                }
            }
        }
    }
}