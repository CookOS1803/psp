package com.cookos;

import javax.swing.JFormattedTextField;

public class TextToNumberMatrixFactory implements IFactory<Number> {

    private Matrix<JFormattedTextField> matrix;
    private int r = 0;
    private int c = 0;
    
    public TextToNumberMatrixFactory(Matrix<JFormattedTextField> matrix) {
        this.matrix = matrix;
    }

    @Override
    public Number getNew() {

        var result = (Number)matrix.get(r, c).getValue();

        c++;
        if (c == matrix.columns())
        {
            c = 0;
            r++;
        }

        return result;
    }
    
}
