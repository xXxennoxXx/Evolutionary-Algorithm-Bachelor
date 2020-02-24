package application;

import algorithms.Algorithm;
import data.Data;

import java.util.concurrent.Callable;

public class Task implements Callable<Boolean> {
    private Data data;
    private Algorithm algorithm;

    public Task(Data data, Algorithm algorithm) {
        this.data = data;
        this.algorithm = algorithm;
    }

    @Override
    public Boolean  call() throws Exception {
        algorithm.apply();

        return null;
    }
}
