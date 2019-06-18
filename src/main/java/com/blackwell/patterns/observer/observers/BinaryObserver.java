package com.blackwell.patterns.observer.observers;

import com.blackwell.patterns.observer.Subject;

public class BinaryObserver implements Observer {

    private Subject subject;

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void update() {
        System.out.println("Binary: " + Integer.toBinaryString(subject.getState()));
    }
}
