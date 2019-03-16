package com.blackwell.datascience.entity;

public class DataPoint<T extends Number> implements Comparable{
    private T num;
    private int count = 1;

    @Override
    public int hashCode() {
        return num.intValue();
    }

    public DataPoint(T num) {
        this.num = num;
    }

    public void increase() { count++; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataPoint))
            return false;
        if (obj == this)
            return true;
        return num.equals(((DataPoint) obj).num);
    }


    public int compareTo(Object other) {


        if(count != ((DataPoint) other).count)
            return ((DataPoint) other).count - count;
        else {
            float n1 = num.floatValue();
            float n2 = ((DataPoint) other).num.floatValue();

            return (int) (n2 - n1);
        }

    }

    public T getNum() {
        return num;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "num=" + num +
                ", count=" + count +
                '}';
    }
}
