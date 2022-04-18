package com.diningPhilosopher;

import java.util.Random;

public class Philosopher implements Runnable{
    private final int id;
    private Fork leftFork;
    private Fork rightFork;
    private boolean hasEaten = false;
    private final int thinkingRandomness;
    private final int eatingRandomness;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.thinkingRandomness = new Random().nextInt(10);
        this.eatingRandomness = new Random().nextInt(5);
    }

    // Describe the states
    private void thinking() throws InterruptedException {
        System.out.println(this + " is thinking...");
         Thread.sleep(thinkingRandomness * 1000L); // Sleep for a random amt of time
    }

    private void eating() throws InterruptedException{
        System.out.println(this + " is eating...");
        Thread.sleep(eatingRandomness* 1000L);
    }

    public void setEatenStatus(boolean status) {
        this.hasEaten = status;
    }

    public int getEatingRandomness() {
        return this.eatingRandomness;
    }

    @Override
    public void run(){
        try{
            while(!hasEaten) {
                thinking();
                if(leftFork.startUsingFork(this, Environment.FORK_STATE.LEFT)) {
                    if ((rightFork.startUsingFork(this, Environment.FORK_STATE.RIGHT))) {
                        eating();
                        this.hasEaten = true;
                        System.out.println("\n--------\n"+this+" has finished eating\n--------\n");
                        rightFork.stopUsingFork(this, Environment.FORK_STATE.RIGHT);
                    }
                    else{
                        System.out.println(this + " can't pick up Right fork.");
                        continue;
                    }
                    leftFork.stopUsingFork(this, Environment.FORK_STATE.LEFT);
                }
                else
                    System.out.println(this + " can't pick up Left fork.");
            }
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
