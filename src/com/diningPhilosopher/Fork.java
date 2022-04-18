package com.diningPhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final int forkID;
    private Lock lock;

    public Fork(int forkID) {
        this.forkID = forkID;
        this.lock = new ReentrantLock();
    }

    public boolean startUsingFork(Philosopher phil, Environment.FORK_STATE forkState) throws InterruptedException{
        // Try to lock the fork
        if (lock.tryLock(5, TimeUnit.MILLISECONDS)) {
            System.out.println(phil + " picked up the " + forkState.toString() + " " + this);
            return  true;
        }

        return false;
    }

    public void stopUsingFork(Philosopher phil, Environment.FORK_STATE forkState) {
        lock.unlock();  // Allow other philosophers to access this
        System.out.println(phil + " has put down " + forkState.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Fork{" +
                "forkID=" + forkID +
                '}';
    }
}