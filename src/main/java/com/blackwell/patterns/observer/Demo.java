package com.blackwell.patterns.observer;

import com.blackwell.patterns.observer.observers.BinaryObserver;
import com.blackwell.patterns.observer.observers.HexObserver;

public class Demo {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexObserver(subject);
        new BinaryObserver(subject);

        int[] testIntArray = {0,1,2,3,4};
        for(int i : testIntArray)
            subject.setState(i);
    }

}
