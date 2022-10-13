package com.cookos;

import java.awt.Dimension;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;

public class MatrixCellFactory implements IFactory<JFormattedTextField> {

    private float defaultValue;
    private int width;
    private int height;

    public MatrixCellFactory(float defaultValue, int width, int height)
    {
        this.defaultValue = defaultValue;
        this.width = width;
        this.height = height;
    }

    @Override
    public JFormattedTextField getNew() {
        
        var result = new JFormattedTextField(NumberFormat.getNumberInstance());
        result.setHorizontalAlignment(JFormattedTextField.CENTER);
        result.setValue(defaultValue);
        result.setPreferredSize(new Dimension(width, height));

        return result;
    }
    
}
