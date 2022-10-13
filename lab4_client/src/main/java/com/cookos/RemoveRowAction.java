package com.cookos;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class RemoveRowAction extends AbstractAction {
    
    private Matrix<JFormattedTextField> matrix;
    private JPanel panel;

    public RemoveRowAction(String label, Matrix<JFormattedTextField> matrix, JPanel panel)
    {
        super(label);
        this.matrix = matrix;
        this.panel = panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (matrix.rows() == 1)
            return;

        for (int i = 0; i < matrix.columns(); i++) {
            panel.remove(matrix.get(matrix.rows() - 1, i));
        }

        matrix.removeRow();
        
        var layout = (GridLayout)panel.getLayout();
        layout.setRows(matrix.rows());

        panel.updateUI();
    }
}
