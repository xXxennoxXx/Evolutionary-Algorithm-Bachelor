package application.evolve.strategy.prob;

import java.util.ArrayList;
import java.util.List;

public class LiniowyZPrawdo implements Probs {

    @Override
    public List<Double> getProb(Integer sizeOfPopulation) {
        List<Double> prob = new ArrayList<>(List.of(0.0));

        double a = (double) 100 / sizeOfPopulation;
        for (int i = 1; i < sizeOfPopulation; i++) {
            double v = (double) i * a + prob.get(i - 1);
            prob.add(v);
        }
        return prob;
    }
}