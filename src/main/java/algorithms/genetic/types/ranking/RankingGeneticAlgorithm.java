package algorithms.genetic.types.ranking;

import algorithms.genetic.GeneticAlgorithm;
import algorithms.genetic.Population;
import models.Model;
import models.ParametersSet;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RankingGeneticAlgorithm extends GeneticAlgorithm {
    private List<Double> probability;

    private RankingGeneticAlgorithm(Integer countOfPopulations, Integer countOfSets, Model modelName) {
        super(countOfPopulations, countOfSets, modelName);
    }

    public RankingGeneticAlgorithm(Integer countOfPopulations, Integer countOfSets, Model modelName, ProbType probType) {
        super(countOfPopulations, countOfSets, modelName);
        probability = probType.getProbability(countOfSets);
    }

    @Override
    public <P extends ParametersSet> Population<P> evolve(Population<P> population) {
        double max = probability.get(probability.size() - 1);
        for (int i = 0; i < countOfSets; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < probability.get(i)) {
                population.get(i).mutate();
                data.getChoice().add(i);
            }
        }
        for (int i = 0; i < countOfSets; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, max);
            if (c < probability.get(i)) {
                int e = new Random().nextInt(countOfSets);
                double d = ThreadLocalRandom.current().nextDouble(0, max);
                if (d < probability.get(e)) {
                    population.get(i).cross(population.get(e));
                    data.getChoice().add(i);
                    data.getChoice().add(e);
                }
            }
        }
        return null;
    }
}
