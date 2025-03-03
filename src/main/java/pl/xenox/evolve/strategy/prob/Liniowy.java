package pl.xenox.evolve.strategy.prob;

import java.util.ArrayList;
import java.util.List;

public class Liniowy implements Probs {


    @Override
    public List<Double> getProb(Integer sizeOfPopulation) {
        List<Double> prob = new ArrayList<>();
        int o = 0;
        double a = (double) 100 / sizeOfPopulation;
        for (int i = 0; i < sizeOfPopulation; i++) {
            double v = (double) o * a;
            prob.add(v);
            o++;
        }
        return prob;
    }
}