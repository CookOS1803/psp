package com.cookos;

import java.net.*;

import javax.swing.JFormattedTextField;

import java.io.*;

public class App {
    public static void main(String[] arg) throws Exception {

        System.out.println("d");

        var serverSocket = new ServerSocket(8080);

        try (var socket = serverSocket.accept()) {

            System.out.println("DAA");

            var ostream = new ObjectOutputStream(socket.getOutputStream());
            var istream = new ObjectInputStream(socket.getInputStream());

            var matrix = (Matrix<JFormattedTextField>)istream.readObject();

            matrix.forEach((e) -> System.out.println(e.getText()));

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
