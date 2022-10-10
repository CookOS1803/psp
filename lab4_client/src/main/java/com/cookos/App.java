package com.cookos;

import java.net.*;
import java.text.NumberFormat;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

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
                value.setHorizontalAlignment(JFormattedTextField.CENTER);
                value.setPreferredSize(new Dimension(40, 40));
                matrix.set(i, j, value);
            }
        }

        var mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new BorderLayout());
  
        var headerLabel = new JLabel("",JLabel.CENTER);
        
        var controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        
        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
          
        mainFrame.add(headerLabel, BorderLayout.NORTH);
        mainFrame.add(controlPanel, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        var bg = new JPanel();
        bg.setLayout(new BorderLayout());
        controlPanel.add(bg);

        JPanel matrixGrid = new JPanel();
        matrixGrid.setBackground(Color.darkGray);
        
        matrixGrid.setLayout(new GridLayout(matrix.rows(), matrix.columns(), 10, 10));
        matrix.forEach((e) -> matrixGrid.add(e));
        bg.add(matrixGrid, BorderLayout.CENTER);
        bg.add(new JButton("-"), BorderLayout.WEST);
        bg.add(new JButton("+"), BorderLayout.EAST);
        bg.add(new JButton("-"), BorderLayout.NORTH);
        bg.add(new JButton("+"), BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        buttonPanel.add(new JButton(new InputAction("send", ostream, matrix)));

        Socket socket = null;

        try {
            headerLabel.setText("ga");

            socket = new Socket("127.0.0.1", 8080);

            reader = new BufferedReader(new InputStreamReader(System.in));
            ostream = new ObjectOutputStream(socket.getOutputStream());
            istream = new ObjectInputStream(socket.getInputStream());
            
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
