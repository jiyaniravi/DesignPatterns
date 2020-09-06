package com.af.proxy.property;

import java.util.Objects;

class Property<T>{
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return value!=null?Objects.hash(value):0;
    }
}

class Creature{
    private int agility;

    public Creature(int agility) {
        this.agility = 512;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

}

public class Demo {
    public static void main(String[] args) {

    }
}
