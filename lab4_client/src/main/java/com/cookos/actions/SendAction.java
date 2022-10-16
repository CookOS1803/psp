package com.cookos.actions;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;

import com.cookos.Matrix;
import com.cookos.factories.*;

public class SendAction extends AbstractAction {

    private ObjectOutputStream ostream;
    private Matrix<JFormattedTextField> matrix;

    public SendAction(String label, ObjectOutputStream ostream, Matrix<JFormattedTextField> matrix)
    {
        super(label);
        this.ostream = ostream;
        this.matrix = matrix;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            var floatMatrix = new Matrix<Float>(matrix.rows(), matrix.columns(), new TextToNumberMatrixFactory(matrix));

            ostream.writeObject(floatMatrix);
            ostream.flush();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }
    
}
