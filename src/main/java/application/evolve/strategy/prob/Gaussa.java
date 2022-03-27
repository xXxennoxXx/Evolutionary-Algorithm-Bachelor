package application.evolve.strategy.prob;

import java.util.ArrayList;
import java.util.List;

public class Gaussa implements Probs {

    @Override
    public List<Double> getProb(Integer sizeOfPopulation) {
        int sigma = sizeOfPopulation / 3;
        List<Double> prob = new ArrayList<>(List.of(0.0));
        double range = (double) 100 / sizeOfPopulation;
        for (int i = 0; i < sizeOfPopulation - 1; i++) {
            double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
                    * Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
            double aa = f * range;
            prob.add(aa);
        }
        return prob;
    }
}