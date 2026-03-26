package com.mason.libgui.utils.structures.states.enumState;

public interface EnumState<T extends Enum<T>> extends EnumQuery<T>{

    void setState(T state);

}
