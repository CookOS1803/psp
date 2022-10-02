package com.cookos;

import java.net.*;
import java.io.*;

public class App {
    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 8080)) {
            
            var reader = new BufferedReader(new InputStreamReader(System.in));
            var ostream = new ObjectOutputStream(socket.getOutputStream());
            var istream = new ObjectInputStream(socket.getInputStream());

            System.out.println(istream.readObject());
            
            reader.close();
            istream.close();
            ostream.close();
        }
        catch (Exception e) {
            
        }
    }
}
