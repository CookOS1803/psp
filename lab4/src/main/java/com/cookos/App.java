package com.cookos;

import java.net.*;
import java.io.*;

public class App {
    private static ObjectOutputStream ostream;
    private static ObjectInputStream istream;

    public static void main(String[] arg) throws Exception {

        var serverSocket = new ServerSocket(8080);

        try (var socket = serverSocket.accept()) {

            ostream = new ObjectOutputStream(socket.getOutputStream());
            ostream.flush();
            istream = new ObjectInputStream(socket.getInputStream());
            
            
            while (true) {
                var matrix = (Matrix<Float>)istream.readObject();

                System.out.println(socket.getInetAddress() + ":" + socket.getPort());
                System.out.println("Matrix:");
                printMatrix(matrix);

                var max = matrix.get(0, 0);
                var min = matrix.get(0, 0);
                int minRow = 0;
                int maxColumn = 0;
                
                for (int i = 0; i < matrix.rows(); i++) {
                    for (int j = 0; j < matrix.columns(); j++) {
                        
                        var current = matrix.get(i, j);

                        if (current.compareTo(max) > 0)
                        {
                            max = current;
                            maxColumn = j;
                        }
                        else if (current.compareTo(min) < 0)
                        {
                            min = current;
                            minRow = i;
                        }

                    }
                }

                var v = matrix.get(minRow, maxColumn);
                System.out.println("Answer: " + v + "\n");
                ostream.writeFloat(v);
                ostream.flush();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            serverSocket.close();

            istream.close();
            ostream.close();
        }
    }

    private static <T> void printMatrix(Matrix<T> matrix) {
        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.columns(); j++) {
                System.out.print(matrix.get(i, j).toString() + "\t");
            }
            System.out.println();                    
        }
        System.out.println();
    }
}
