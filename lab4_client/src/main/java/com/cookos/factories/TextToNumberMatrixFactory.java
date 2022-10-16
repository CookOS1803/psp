package com.cookos.factories;

import javax.swing.JFormattedTextField;

import com.cookos.Matrix;

public class TextToNumberMatrixFactory implements IFactory<Float> {

    private Matrix<JFormattedTextField> matrix;
    private int r = 0;
    private int c = 0;
    
    public TextToNumberMatrixFactory(Matrix<JFormattedTextField> matrix) {
        this.matrix = matrix;
    }

    @Override
    public Float getNew() {

        var result = ((Number)matrix.get(r, c).getValue()).floatValue();

        c++;
        if (c == matrix.columns())
        {
            c = 0;
            r++;
        }

        return result;
    }
    
}
