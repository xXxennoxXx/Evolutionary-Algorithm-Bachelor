package algorithms.genetic;

import models.ParametersSet;

public interface Geneticable {
    void cross(ParametersSet p);

    void mutate();

    void initRandom();
}
