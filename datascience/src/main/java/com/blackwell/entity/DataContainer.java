package com.blackwell.entity;

import java.util.ArrayList;

public class DataContainer extends ArrayList<DataPoint<Float>> {
    @Override
    public boolean add(DataPoint<Float> floatDataPoint) {
        for (DataPoint point : this){
            if (point.equals(floatDataPoint)){
                point.increase();
                return true;
            }
        }
        super.add(floatDataPoint);

        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Object point : this)
            sb.append(point).append(System.lineSeparator());

        return sb.toString();
    }

}
