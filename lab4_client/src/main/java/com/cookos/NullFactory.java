package com.cookos;

public class NullFactory<T> implements IFactory<T> {

    @Override
    public T getNew() {
        return null;
    }
    
}
