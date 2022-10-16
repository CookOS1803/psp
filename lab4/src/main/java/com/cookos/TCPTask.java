package com.cookos;

import java.net.*;
import java.io.*;

public class TCPTask implements Runnable
{
    private ObjectOutputStream ostream;
    private ObjectInputStream istream;
    private Socket socket;

    public TCPTask(Socket socket)
    {
        this.socket = socket;
        
        try {
            ostream = new ObjectOutputStream(socket.getOutputStream());
            ostream.flush();
            istream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            
        }    
    }

    @Override
    public void run() {
        
        App.printAdress(socket);
        System.out.println(" connected\n");

        try {
            while (true) {
                var matrix = (Matrix<Float>)istream.readObject();

                App.printAdress(socket);
                System.out.println("\nMatrix:");
                App.printMatrix(matrix);

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
        catch (IOException e) {
        }
        catch (ClassNotFoundException e) {
        }
        finally
        {
            App.printAdress(socket);
            System.out.println(" has disconnected\n");

            try {
                socket.close();
                ostream.close();
                istream.close();
            } catch (IOException e) {
            }
        }
    }
    
}
