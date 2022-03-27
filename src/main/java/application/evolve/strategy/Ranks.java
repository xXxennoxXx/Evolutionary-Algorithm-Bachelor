package application.evolve.strategy;

import application.ParametrPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Ranks implements Evolution {
    List<Double> prob = new ArrayList<>();

    @Override
    public void evolve(List<ParametrPoint> listOfParametrs) {
        int population = listOfParametrs.size();
        double max = prob.get(prob.size() - 1);
        for (int i = 0; i < population; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < prob.get(i)) {
                listOfParametrs.get(i).mutate();
            }
        }
        for (int i = 0; i < population; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < prob.get(i)) {
                int e = new Random().nextInt(population);
                double d = ThreadLocalRandom.current().nextDouble(0, max);
                if (d < prob.get(e)) {
                    ParametrPoint.cross(listOfParametrs.get(i), listOfParametrs.get(e));
                }
            }
        }
    }


}
