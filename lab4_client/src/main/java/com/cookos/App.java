package com.cookos;

import java.net.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;

public class App {

    private static BufferedReader reader;
    private static ObjectOutputStream ostream;
    private static ObjectInputStream istream;

    public static void main(String[] args) {

        var matrix = new Matrix<JFormattedTextField>(3, 3, new MatrixCellFactory("0", 40, 40));

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

        var gridPanel = new JPanel();
        gridPanel.setLayout(new BorderLayout());
        controlPanel.add(gridPanel);

        JPanel matrixGrid = new JPanel();
        matrixGrid.setBackground(Color.darkGray);
        
        matrixGrid.setLayout(new GridLayout(matrix.rows(), matrix.columns(), 10, 10));
        matrix.forEach((e) -> matrixGrid.add(e));
        gridPanel.add(matrixGrid, BorderLayout.CENTER);
        gridPanel.add(new JButton("-"), BorderLayout.WEST);
        gridPanel.add(new JButton("+"), BorderLayout.EAST);
        gridPanel.add(new JButton("-"), BorderLayout.NORTH);
        gridPanel.add(new JButton(new AddRowAction("+", matrix, matrixGrid)), BorderLayout.SOUTH);
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
