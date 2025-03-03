package pl.xenox;

import pl.xenox.algorithm.Algorithm;
import pl.xenox.evolve.strategy.Evolution;
import pl.xenox.evolve.strategy.prob.ProbsEnum;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Builder
public class MainFlow {
    private ProbsEnum prob;
    private Evolution evolution;
    private Algorithm algorithm;
    private List<Double> probs;

    public static class Params {
        int sizeOfPopulation = -1;
        int numberOfPopulation = 10000;
        int crossChance;
        int mutateChance;
        ProbsEnum prob;

    }

    public void calculate(Params params) {
        List<Point> points = DataLoader.load();
        Timer timer = new Timer();
        int sizeOfPopulation = params.sizeOfPopulation;
        int numberOfPopulation = params.numberOfPopulation;
        int crossChance = params.crossChance;
        int mutateChance = params.mutateChance;

        List<ParametrPoint> parameters2 = new ArrayList<>();

        for (int i = 0; i < sizeOfPopulation; i++) {
            ParametrPoint parameters = new ParametrPoint();
            parameters.generateParameters();
            parameters2.add(parameters);
        }

        probs = params.prob.get().getProb(sizeOfPopulation);
        List<String> def = new ArrayList<>();
        for (int j = 0; j < numberOfPopulation; j++) {
            for (int i = 0; i < sizeOfPopulation; i++) {
                algorithm.calculate(parameters2.get(i), points);
                System.out.println(j + "   " + i);
            }
            parameters2.sort(Comparator.comparing(parametrPoint -> parametrPoint.deflection));
            def.add(parameters2.get(0).deflection.toString());
            if (j != numberOfPopulation - 1) {
                evolution.evolve(parameters2);
            }

        }
        algorithm.calculate(parameters2.get(0), points);

        DataLoader.save(List.of(timer.getTime()), "Czas.txt");
        DataLoader.save(def, "Deflection.txt");
        DataLoader.save(parameters2, "Parametry.txt");
        DataLoader.save(points, "Wynik.txt");
    }
}
