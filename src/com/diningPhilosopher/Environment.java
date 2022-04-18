package com.diningPhilosopher;

public class Environment {
    public static final int NUM_OF_PHILOSOPHERS = 5;
    public static final int NUM_OF_FORKS = 5;
    public static final int SIM_EATING_TIME = 5 * 1000;

    public enum FORK_STATE {
        LEFT,
        RIGHT
    };
}
