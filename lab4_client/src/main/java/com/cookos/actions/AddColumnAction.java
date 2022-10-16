package com.cookos.actions;

import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import com.cookos.Matrix;
import com.cookos.factories.*;

public class AddColumnAction extends AbstractAction {

    private Matrix<JFormattedTextField> matrix;
    private JPanel panel;
    private MatrixCellFactory factory;

    public AddColumnAction(String label, Matrix<JFormattedTextField> matrix, JPanel panel, MatrixCellFactory factory)
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

        panel.removeAll();
        matrix.forEach(c -> panel.add(c));

        panel.updateUI();
    }
    
}
