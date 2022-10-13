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
            
            var floatMatrix = new Matrix<Number>(matrix.rows(), matrix.columns(), new TextToNumberMatrixFactory(matrix));

            for (int i = 0; i < floatMatrix.rows(); i++) {
                for (int j = 0; j < floatMatrix.columns(); j++) {
                    System.out.print(floatMatrix.get(i, j) + " ");
                }
                System.out.println();
                
            }

            ostream.writeObject(floatMatrix);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }
    
}
