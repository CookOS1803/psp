package com.cookos;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {

        var frame = new JFrame("okno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        
        var pane = new JPanel();
        
        var text = new JLabel();        
        frame.add(text, BorderLayout.WEST);

        
        var button = new JButton(new MoveAction("Move Left", text, -10));
        var button2 = new JButton(new MoveAction("Move Right", text, 10));
        pane.add(button);
        pane.add(button2);
        frame.add(pane, BorderLayout.NORTH);
        
        try (var socket = new Socket("127.0.0.1", 8080)) {
            
            var reader = new BufferedReader(new InputStreamReader(System.in));
            var ostream = new ObjectOutputStream(socket.getOutputStream());
            var istream = new ObjectInputStream(socket.getInputStream());

            text.setText(istream.readObject().toString());
            
            reader.close();
            istream.close();
            ostream.close();
        }
        catch (Exception e) {
            text.setText("can't connect");
        }
    }
}
