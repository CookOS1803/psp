package com.cookos;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class AddRowAction extends AbstractAction {

    private Matrix<JFormattedTextField> matrix;
    private JPanel panel;
    private MatrixCellFactory factory;

    public AddRowAction(String label, Matrix<JFormattedTextField> matrix, JPanel panel, MatrixCellFactory factory)
    {
        super(label);
        this.matrix = matrix;
        this.panel = panel;
        this.factory = factory;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        matrix.addRow(factory);

        var layout = (GridLayout)panel.getLayout();
        layout.setRows(matrix.rows());

        for (int i = 0; i < matrix.columns(); i++) {
            panel.add(matrix.get(matrix.rows() - 1, i));
        }
        panel.updateUI();
    }
    
}
