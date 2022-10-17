package com.cookos;

import java.net.*;
import java.io.*;

public class App {

    public static void main(String[] arg) throws Exception {

        try (var serverSocket = new ServerSocket(8080)) {

            while (true) {
                var socket = serverSocket.accept();

                var thread = new Thread(new TCPTask(socket));
                thread.start();
            }           

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void printMatrix(Matrix<T> matrix) {
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.columns(); j++) {
                System.out.print(matrix.get(i, j).toString() + "\t");
            }
            System.out.println();                    
        }
        System.out.println();
    }

    public static void printAdress(Socket socket)
    {
        System.out.print(socket.getInetAddress() + ":" + socket.getPort());
    }
}
