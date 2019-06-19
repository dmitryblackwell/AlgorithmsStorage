package com.blackwell.observer.observers;

import com.blackwell.observer.Subject;

public class HexObserver implements Observer {

    private Subject subject;

    public HexObserver(Subject subject) {
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void update() {
        System.out.println("Hex: " + Integer.toHexString(subject.getState()));
    }
}
