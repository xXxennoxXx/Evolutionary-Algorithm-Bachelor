package algorithms.genetic.types;

import algorithms.genetic.GeneticAlgorithm;
import algorithms.genetic.Geneticable;
import algorithms.genetic.Population;
import models.Model;
import models.ParametersSet;

import java.util.Random;

public class TabGeneticAlgorithm extends GeneticAlgorithm {

    public TabGeneticAlgorithm(Integer countOfPopulations, Integer countOfSets, Model modelName) {
        super(countOfPopulations, countOfSets, modelName);
    }

    @Override
    public <P extends ParametersSet> Population<P> evolve(Population<P> population) {

        int stay = countOfPopulations / 4;

        //Excluding same parameters sets
        for (int b = 0; b < countOfPopulations; b++) {
            for (int a = b + 1; a < countOfPopulations; a++) {
                if (population.get(a).equals(population.get(b))) {
                    population.get(a).mutate();
                }
            }
        }

        //Crossing parameters sets
        for (int c = 0; c < stay; c++) {
            int d = new Random().nextInt(stay - c) + c + 1;
            population.get(c).cross(population.get(d));
        }

        //Mutate population
        for (Geneticable g : population) g.mutate();

        return population;
    }
}
