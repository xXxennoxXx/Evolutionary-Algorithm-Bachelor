package algorithms.genetic;

import models.Model;
import models.ParametersSet;

import java.util.ArrayList;
import java.util.Comparator;

public class Population<T extends ParametersSet> extends ArrayList<T> {

    public Population(Integer sizeOfPopulation, Model model) {
        for (int i = 0; i < sizeOfPopulation; i++) add((T) model.getNewParametersSet());
    }

    public void initParameters() {
        for (ParametersSet p : this) initParameters();
    }


    public void sortParameters() {
        sort(Comparator.comparingDouble(ParametersSet::getDeflection));
    }
}
