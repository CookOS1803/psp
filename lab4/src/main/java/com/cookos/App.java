package com.cookos;

import java.net.*;

import javax.swing.JFormattedTextField;

import java.io.*;

public class App {
    private static ObjectOutputStream ostream;
    private static ObjectInputStream istream;

    public static void main(String[] arg) throws Exception {

        System.out.println("d");

        var serverSocket = new ServerSocket(8080);

        try (var socket = serverSocket.accept()) {

            System.out.println("DAA");

            ostream = new ObjectOutputStream(socket.getOutputStream());
            istream = new ObjectInputStream(socket.getInputStream());
            
            while (true) {
                var matrix = (Matrix<Number>)istream.readObject();

                for (int i = 0; i < matrix.rows(); i++) {
                    for (int j = 0; j < matrix.columns(); j++) {
                        System.out.print(matrix.get(i, j) + " ");
                    }
                    System.out.println();
                    
                }
                
                System.out.println();
            }

        }
        catch (Exception e) {
            
        }
        finally {
            serverSocket.close();

            istream.close();
            ostream.close();
        }

        System.out.println("Hello World!");
    }
}
