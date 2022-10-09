package com.cookos;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;

public class InputAction extends AbstractAction {

    private ObjectOutputStream ostream;
    private Matrix<JFormattedTextField> matrix;

    public InputAction(String label, ObjectOutputStream ostream, Matrix<JFormattedTextField> matrix)
    {
        super(label);
        this.ostream = ostream;
        this.matrix = matrix;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ostream.writeObject(matrix);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }
    
}
