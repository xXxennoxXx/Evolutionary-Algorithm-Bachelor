package models;

import algorithms.genetic.Geneticable;

public class ParametersSet implements Geneticable {
    private Double deflection;

    public Double getDeflection() {
        return deflection;
    }

    public void setDeflection(Double deflection) {
        this.deflection = deflection;
    }

    @Override
    public void cross(ParametersSet p1, ParametersSet p2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mutate(ParametersSet p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void initRandom() {
        throw new UnsupportedOperationException();
    }
}
