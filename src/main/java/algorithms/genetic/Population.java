package algorithms.genetic;

import application.ParametrPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population<T> extends ArrayList<T> {
    public void setParameters() {
        ParametrPoint parameters = new ParametrPoint();
        parameters.generateParameters();
        listOfParametrs.add(parameters);
    }




    public void sortParameters() {
        Collections.sort(listOfParametrs, new Comparator<ParametrPoint>() {
            @Override
            public int compare(ParametrPoint arg0, ParametrPoint arg1) {
                int ret = arg0.deflection.compareTo(arg1.deflection);
                return ret;
            }
        });
    }
}
