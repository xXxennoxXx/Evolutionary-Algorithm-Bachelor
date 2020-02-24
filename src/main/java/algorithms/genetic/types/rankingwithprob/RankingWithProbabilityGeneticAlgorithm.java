package algorithms.genetic.types.rankingwithprob;

import algorithms.genetic.GeneticAlgorithm;
import algorithms.genetic.Population;
import models.Model;
import models.ParametersSet;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RankingWithProbabilityGeneticAlgorithm extends GeneticAlgorithm {

    private List<Double> probability;
    private Integer mutateChance, crossChance;

    private RankingWithProbabilityGeneticAlgorithm(Integer countOfPopulations, Integer countOfSets, Model modelName) {
        super(countOfPopulations, countOfSets, modelName);
    }

    public RankingWithProbabilityGeneticAlgorithm(Integer countOfPopulations,
                                                  Integer countOfSets,
                                                  Model modelName,
                                                  ProbType probType,
                                                  Integer mutateChance,
                                                  Integer crossChance) {
        super(countOfPopulations, countOfSets, modelName);
        probability = probType.getProbability(countOfSets);
        this.mutateChance = mutateChance;
        this.crossChance = crossChance;
    }

    @Override
    public <P extends ParametersSet> Population<P> evolve(Population<P> population) {


        int countMutate = countOfSets * mutateChance / 100;
        int countCross = countOfSets * crossChance / 100;
        for (int i = 0; i < countMutate; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, probability.get(probability.size() - 1));
            int j = 0;
            while (c > probability.get(j)) {
                j++;
            }
            population.get(j).mutate();
            data.getChoice().add(j);
        }
        for (int i = 0; i < countCross; i++) {
            double c = ThreadLocalRandom.current().nextDouble(0, probability.get(probability.size() - 1));
            double d = ThreadLocalRandom.current().nextDouble(0, probability.get(probability.size() - 1));
            int j = 0;
            int k = 0;
            while (c > probability.get(j)) {
                j++;
            }
            while (d > probability.get(k)) {
                k++;
            }
            population.get(j).cross(population.get(k));
            data.getChoice().add(j);
            data.getChoice().add(k);
        }


        return null;
    }
}
