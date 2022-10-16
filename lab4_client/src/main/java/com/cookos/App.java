package com.cookos;

import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import com.cookos.actions.*;
import com.cookos.factories.*;

public class App {

    private static ObjectOutputStream ostream;
    private static ObjectInputStream istream;

    public static void main(String[] args) {

        var factory = new MatrixCellFactory(0f, 40, 40);
        var matrix = new Matrix<JFormattedTextField>(3, 3, factory);

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
        gridPanel.add(new JButton(new RemoveColumnAction("-", matrix, matrixGrid)), BorderLayout.WEST);
        gridPanel.add(new JButton(new AddColumnAction("+", matrix, matrixGrid, factory)), BorderLayout.EAST);
        gridPanel.add(new JButton(new RemoveRowAction("-", matrix, matrixGrid)), BorderLayout.NORTH);
        gridPanel.add(new JButton(new AddRowAction("+", matrix, matrixGrid, factory)), BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
        Socket socket = null;

        try {
            headerLabel.setText("ga");

            socket = new Socket("127.0.0.1", 8080);

            ostream = new ObjectOutputStream(socket.getOutputStream());
            ostream.flush();
            istream = new ObjectInputStream(socket.getInputStream());

            buttonPanel.add(new JButton(new SendAction("send", ostream, matrix)));
            buttonPanel.updateUI();
            
            while (true)
            {
                float v = istream.readFloat();
                headerLabel.setText(Float.toString(v));
                headerLabel.updateUI();
            }
        }
        catch (Exception e) {
            headerLabel.setText("can't connect");
            e.printStackTrace();

            try {
                istream.close();
                ostream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
