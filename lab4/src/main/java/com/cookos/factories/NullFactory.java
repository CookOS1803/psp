package com.cookos.factories;

public class NullFactory<T> implements IFactory<T> {

    @Override
    public T getNew() {
        return null;
    }
    
}
