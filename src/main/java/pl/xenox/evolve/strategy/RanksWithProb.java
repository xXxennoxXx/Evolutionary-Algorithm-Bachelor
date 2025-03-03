package pl.xenox.evolve.strategy;

import pl.xenox.ParametrPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RanksWithProb implements Evolution {

    public int mutateChance;
    public int crossChance;
    ArrayList<Double> prob = new ArrayList<>();

    @Override
    public void evolve(List<ParametrPoint> listOfParametrs) {
        int population = listOfParametrs.size();
        int countMutate = population * mutateChance / 100;
        int countCross = population * crossChance / 100;
        for (int i = 0; i < countMutate; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
            int j = 0;
            while (c > prob.get(j)) {
                j++;
            }
            listOfParametrs.get(j).mutate();
        }
        for (int i = 0; i < countCross; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
            double d = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
            int j = 0;
            int k = 0;
            while (c > prob.get(j)) {
                j++;
            }
            while (d > prob.get(k)) {
                k++;
            }
            ParametrPoint.cross(listOfParametrs.get(j), listOfParametrs.get(k));
        }
    }

}
