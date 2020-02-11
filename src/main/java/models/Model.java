package models;

import data.Data;
import exceptions.WrongParametersSetsException;

public interface Model {

    public Double calculate(ParametersSet parametersSet, Data data) throws WrongParametersSetsException;


}
