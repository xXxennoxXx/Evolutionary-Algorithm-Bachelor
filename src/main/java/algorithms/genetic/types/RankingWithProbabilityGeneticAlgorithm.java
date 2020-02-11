package algorithms.genetic.types;

import algorithms.genetic.GeneticAlgorithm;
import algorithms.genetic.Population;
import application.ParametrPoint;

import java.util.concurrent.ThreadLocalRandom;

public class RankingWithProbabilityGeneticAlgorithm<T> extends GeneticAlgorithm<T> {


    @Override
    public Population<T> evolve(Population<T> population) {

        int countMutate = population * mutateChance / 100;
        int countCross = population * crossChance / 100;
        for (int i = 0; i < countMutate; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
            int j = 0;
            while (c > prob.get(j)) {
                j++;
            }
            listOfParametrs.get(j).mutate();
            choice.add(j);
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
            choice.add(j);
            choice.add(k);
        }

        return null;
    }
}
