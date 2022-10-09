package com.cookos;

import java.net.*;
import java.text.NumberFormat;
import java.io.*;
import javax.swing.*;

import java.awt.*;

public class App {

    private static BufferedReader reader;
    private static ObjectOutputStream ostream;
    private static ObjectInputStream istream;

    public static void main(String[] args) {

        var matrix = new Matrix<JFormattedTextField>(5, 3);

        for (int i = 0; i < matrix.rows(); i++) {
            for (int j = 0; j < matrix.columns(); j++) {
                var value = new JFormattedTextField(NumberFormat.getNumberInstance());
                value.setPreferredSize(new Dimension(40, 40));
                matrix.set(i, j, value);
            }
        }

        var mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
  
        var headerLabel = new JLabel("",JLabel.CENTER);
        
        var controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
          
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        
        panel.setLayout(new GridLayout(matrix.rows(), matrix.columns(), 10, 10));
        matrix.forEach((e) -> panel.add(e));
        controlPanel.add(panel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", 8080);

            reader = new BufferedReader(new InputStreamReader(System.in));
            ostream = new ObjectOutputStream(socket.getOutputStream());
            istream = new ObjectInputStream(socket.getInputStream());

            
            mainFrame.add(new JButton(new InputAction("send", ostream, matrix)));
            
        }
        catch (Exception e) {
            headerLabel.setText("can't connect");

            try {
                reader.close();
                istream.close();
                ostream.close();
            } catch (Exception e2) {
            }
        }
    }
}
