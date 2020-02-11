package algorithms.genetic;

public abstract class GeneticAlgorithm<T> {
    public  abstract Population<T> evolve(Population<T> population);


}
