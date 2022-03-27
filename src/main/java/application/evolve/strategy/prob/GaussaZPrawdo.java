package application.evolve.strategy.prob;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

public class GaussaZPrawdo implements Probs {

    @Override
    public List<Double> getProb(Integer sizeOfPopulation) {
        int sigma = sizeOfPopulation / 3;
        List<Double> prob = new ArrayList<>(List.of(0.0));
        double range = (double) 100 / sizeOfPopulation;
        double abc;
        for (int i = 1; i < sizeOfPopulation - 1; i++) {
            double f = 1 / (sigma * Math.sqrt(PI * 2))
                    * Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
            abc = f * range + prob.get(i - 1);
            prob.add(abc);
        }
        return prob;
    }

}
