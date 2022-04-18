package com.diningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        try {
            // Create a list of all forks
            Fork[] forks = new Fork[Environment.NUM_OF_FORKS];
            for(int i=0; i<Environment.NUM_OF_FORKS; i++) {
                forks[i] = new Fork(i);
            }

            // Create a executor thread pool with NUM_OF_PHILOSOPHER threads
            executorService = Executors.newFixedThreadPool(Environment.NUM_OF_PHILOSOPHERS);

            // Create a list of Philosophers
            Philosopher[] philosophers = new Philosopher[Environment.NUM_OF_PHILOSOPHERS];
            for(int i=0; i<Environment.NUM_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, forks[i], forks[(i+1) % Environment.NUM_OF_FORKS ]);
                executorService.execute(philosophers[i]);
            }

            // Put main thread to sleep until a fixed amt of time(all philosophers should have eaten by now)
            Thread.sleep(Environment.SIM_EATING_TIME);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        finally {
            assert executorService != null;
            executorService.shutdown();
            Thread.sleep(1000);
        }
    }
}
