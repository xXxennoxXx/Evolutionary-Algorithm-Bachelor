package algorithms.genetic;

import algorithms.Algorithm;
import data.Data;
import exceptions.WrongParametersSetsException;
import models.Model;
import models.ParametersSet;

public abstract class GeneticAlgorithm implements Algorithm {

    protected Integer countOfPopulations, countOfSets;
    private Model mathModel;
    protected Data data;

    public abstract <P extends ParametersSet> Population<P> evolve(Population<P> population);

    public GeneticAlgorithm(Integer countOfPopulations, Integer countOfSets, Model modelName) {
        this.countOfPopulations = countOfPopulations;
        this.countOfSets = countOfSets;
        mathModel = modelName;
    }

    @Override
    public void apply() {

        //Populate population of parameters sets
        Population<ParametersSet> population = new Population<>(countOfSets, mathModel);


        //Main loop of algorithm
        for (int i = 0; i < countOfPopulations; i++) {

            //Calculation deflection for each parameters sets
            for (ParametersSet ps : population)
                try {
                    ps.setDeflection(mathModel.calculate(ps, data));
                } catch (WrongParametersSetsException e) {
                    e.printStackTrace();
                }


            //Sorting and logging best parameters sets deflection
            population.sortParameters();
            data.getDeflection().add(population.get(0).toString());

            //Proceed evolution
            if (i != countOfPopulations - 1)
                population = evolve(population);
        }
    }
}
