package pl.xenox.algorithm;

import pl.xenox.ParametrPoint;
import pl.xenox.Point;

import java.util.List;

public interface Algorithm {
    void calculate(ParametrPoint parameter, List<Point> listOfPoint);
}
