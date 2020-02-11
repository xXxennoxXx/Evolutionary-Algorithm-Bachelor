package algorithms.genetic.types;

import algorithms.genetic.GeneticAlgorithm;
import algorithms.genetic.Population;
import application.ParametrPoint;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RankingGeneticAlgorithm<T> extends GeneticAlgorithm<T> {

    @Override
    public Population<T> evolve(Population<T> population) {
        double max = prob.get(prob.size() - 1);
        for (int i = 0; i < population; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < prob.get(i)) {
                listOfParametrs.get(i).mutate();
                choice.add(i);
            }
        }
        for (int i = 0; i < population; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < prob.get(i)) {
                int e = new Random().nextInt(population);
                double d = ThreadLocalRandom.current().nextDouble(0, max);
                if (d < prob.get(e)) {
                    ParametrPoint.cross(listOfParametrs.get(i), listOfParametrs.get(e));
                    choice.add(i);
                    choice.add(e);
                }
            }
        }

        return null;
    }

}
