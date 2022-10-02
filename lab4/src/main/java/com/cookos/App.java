package com.cookos;

import java.net.*;
import java.io.*;

public class App {
    public static void main(String[] arg) throws Exception {

        System.out.println("d");

        var serverSocket = new ServerSocket(8080);

        try (var socket = serverSocket.accept()) {

            System.out.println("DAA");

            var ostream = new ObjectOutputStream(socket.getOutputStream());
            var istream = new ObjectInputStream(socket.getInputStream());

            ostream.writeObject("lopl");

            istream.close();
            ostream.close();

        }
        catch (Exception e) {
            
        }
        finally {
            serverSocket.close();
        }

        System.out.println("Hello World!");
    }
}
