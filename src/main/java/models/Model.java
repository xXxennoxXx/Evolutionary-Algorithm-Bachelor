package models;

import data.Data;
import exceptions.WrongParametersSetsException;

public interface Model {

    Double calculate(ParametersSet parametersSet, Data data) throws WrongParametersSetsException;

    ParametersSet getNewParametersSet();

}
