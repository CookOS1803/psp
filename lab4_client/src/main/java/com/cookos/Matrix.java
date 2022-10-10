package com.cookos;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Matrix<T> implements Serializable 
{
    private ArrayList<ArrayList<T>> matrix;

    public Matrix(int x, int y)
    {
        matrix = new ArrayList<ArrayList<T>>();

        for (int i = 0; i < x; i++)
        {
            var arrayList = new ArrayList<T>();

            for (int j = 0; j < y; j++)
            {
                arrayList.add(null);
            }

            matrix.add(arrayList);
        }
    }

    public T get(int x, int y)
    {
        return matrix.get(x).get(y);
    }

    public void set(int x, int y, T value)
    {
        matrix.get(x).set(y, value);
    }

    public int rows()
    {
        return matrix.size();
    }

    public int columns()
    {
        if (matrix.size() == 0)
            return 0;
        else
            return matrix.get(0).size();
    }

    public void forEach(Consumer<? super T> action)
    {
        for (var row : matrix) {
            row.forEach(action);
        }
    }

    public void setSize(int x, int y)
    {
        var newMatrix = new Matrix<T>(x, y);

        for (int i = 0; i < rows() && i < x; i++)
        {
            for (int j = 0; j < columns() && j < y; j++)
            {
                newMatrix.set(i, j, get(i, j));
            }
        }

        matrix = newMatrix.matrix;
    }

    public void addRow()
    {
        var row = new ArrayList<T>();

        for (int i = 0; i < columns(); i++) {
            row.add(null);
        }

        matrix.add(row);
    }

}
