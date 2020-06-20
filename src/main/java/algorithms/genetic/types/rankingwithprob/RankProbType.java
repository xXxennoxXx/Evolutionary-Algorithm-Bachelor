package algorithms.genetic.types.rankingwithprob;

import java.util.ArrayList;
import java.util.List;

public enum RankProbType {

    LINEAR {
        @Override
        public List<Double> getProbability(Integer sizeOfPopulation) {
            List<Double> list = new ArrayList<>();
            list.add(0.0);
            double a = (double) 100 / sizeOfPopulation;
            for (int i = 1; i < sizeOfPopulation; i++) {
                double v = (double) i * a + list.get(i - 1);
                list.add(v);
            }
            return list;
        }
    },
    GAUSS {
        @Override
        public List<Double> getProbability(Integer sizeOfPopulation) {
            List<Double> list = new ArrayList<>();
            int sigma = sizeOfPopulation / 3;
            list.add(0.0);
            double range = (double) 100 / sizeOfPopulation;
            double abc = 0.0;
            for (int i = 1; i < sizeOfPopulation - 1; i++) {
                Double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
                        * Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
                abc = f * range + list.get(i - 1);
                list.add(abc);
            }
            return list;
        }
    };

    public abstract List<Double> getProbability(Integer sizeOfPopulation);

}
