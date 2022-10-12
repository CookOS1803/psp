package com.cookos;

import java.awt.Dimension;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;

public class MatrixCellFactory implements IFactory<JFormattedTextField> {

    private String defaultText;
    private int width;
    private int height;

    public MatrixCellFactory(String defaultText, int width, int height)
    {
        this.defaultText = defaultText;
        this.width = width;
        this.height = height;
    }

    @Override
    public JFormattedTextField getNew() {
        
        var result = new JFormattedTextField(NumberFormat.getNumberInstance());
        result.setHorizontalAlignment(JFormattedTextField.CENTER);
        result.setText(defaultText);
        result.setPreferredSize(new Dimension(width, height));

        return result;
    }
    
}
