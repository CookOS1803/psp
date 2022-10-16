package com.cookos;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import com.cookos.factories.*;

public class Matrix<T> implements Serializable 
{
    private ArrayList<ArrayList<T>> matrix;

    public Matrix(int x, int y, IFactory<T> factory)
    {
        matrix = new ArrayList<ArrayList<T>>();

        for (int i = 0; i < x; i++)
        {
            var arrayList = new ArrayList<T>();

            for (int j = 0; j < y; j++)
            {
                arrayList.add(factory.getNew());
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
        var tempMatrix = new Matrix<T>(x, y, new NullFactory<T>());

        for (int i = 0; i < rows() && i < x; i++)
        {
            for (int j = 0; j < columns() && j < y; j++)
            {
                tempMatrix.set(i, j, get(i, j));
            }
        }

        matrix = tempMatrix.matrix;
    }

    public void addRow(IFactory<T> factory)
    {
        var row = new ArrayList<T>();

        for (int i = 0; i < columns(); i++) {
            row.add(factory.getNew());
        }

        matrix.add(row);
    }

    public void addColumn(IFactory<T> factory)
    {
        for (var row : matrix) {
            row.add(factory.getNew());
        }
    }

    public void removeRow()
    {
        matrix.remove(rows() - 1);
    }

    public void removeColumn()
    {
        for (var row : matrix) {
            row.remove(row.size() - 1);
        }
    }

}
