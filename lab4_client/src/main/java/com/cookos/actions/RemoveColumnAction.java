package com.cookos.actions;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

import com.cookos.Matrix;

public class RemoveColumnAction extends AbstractAction {
    
    private Matrix<JFormattedTextField> matrix;
    private JPanel panel;

    public RemoveColumnAction(String label, Matrix<JFormattedTextField> matrix, JPanel panel)
    {
        super(label);
        this.matrix = matrix;
        this.panel = panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (matrix.columns() == 1)
            return;

        for (int i = 0; i < matrix.rows(); i++) {
            panel.remove(matrix.get(i, matrix.columns() - 1));
        }

        matrix.removeColumn();
        
        var layout = (GridLayout)panel.getLayout();
        layout.setColumns(matrix.columns());

        panel.updateUI();
    }
}
