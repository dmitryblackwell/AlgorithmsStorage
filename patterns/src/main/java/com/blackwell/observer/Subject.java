package com.blackwell.observer;

import com.blackwell.observer.observers.Observer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    List<Observer> observers = new ArrayList<>();

    @Getter
    private int state;


    public void add(Observer o) {
        observers.add(o);
    }

    public void setState(int state) {
        this.state = state;
        this.execute();
    }

    private void execute() {
        for(Observer observer : observers)
            observer.update();
    }

}
