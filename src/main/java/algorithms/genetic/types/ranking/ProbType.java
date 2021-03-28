package algorithms.genetic.types.ranking;

import java.util.ArrayList;
import java.util.List;

public enum ProbType {

    LINEAR {
        @Override
        public List<Double> getProbability(Integer sizeOfPopulation) {
            List<Double> list = new ArrayList<>();
            int o = 0;
            double a = (double) 100 / sizeOfPopulation;
            for (int i = 0; i < sizeOfPopulation; i++) {
                double v = (double) o * a;
                list.add(v);
                o++;
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
            for (int i = 0; i < sizeOfPopulation - 1; i++) {
                Double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
                        * Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
                double aa = f * range;
                list.add(aa);
            }
            return list;
        }
    };

    public abstract List<Double> getProbability(Integer sizeOfPopulation);

}
