package application.algorithm;

import application.ParametrPoint;
import application.Point;

import java.util.List;

public interface Algorithm {
    void calculate(ParametrPoint parameter, List<Point> listOfPoint);
}
