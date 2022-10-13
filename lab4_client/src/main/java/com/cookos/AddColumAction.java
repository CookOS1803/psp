package com.cookos;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class AddColumAction extends AbstractAction {

    private Matrix<JFormattedTextField> matrix;
    private JPanel panel;
    private MatrixCellFactory factory;

    public AddColumAction(String label, Matrix<JFormattedTextField> matrix, JPanel panel, MatrixCellFactory factory)
    {
        super(label);
        this.matrix = matrix;
        this.panel = panel;
        this.factory = factory;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        matrix.addColumn(factory);

        var layout = (GridLayout)panel.getLayout();
        layout.setColumns(matrix.columns());

        for (int i = 0; i < matrix.rows(); i++) {
            panel.add(matrix.get(i, matrix.columns() - 1));
        }
        panel.updateUI();
    }
    
}
